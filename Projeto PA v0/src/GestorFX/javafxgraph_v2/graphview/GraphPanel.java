/* 
 * The MIT License
 *
 * Copyright 2018 brunomnsilva@gmail.com.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package GestorFX.javafxgraph_v2.graphview;

import diGraph.DiGraph;
import graph.Edge;
import graph.Graph;
import graph.Vertex;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;

/**
 * JavaFX pane that is capable of plotting a Graph ADT.
 *
 * Vertices and edges can be dragged by the user. Tooltips are added to vertices and edges so one can inspect the stored value.
 *
 * @author brunomnsilva
 * @param <V> Type of element for the vertices
 * @param <E> Type of element for the edges.
 */
public class GraphPanel<V, E> extends Pane {

    /*
        BEGIN CONFIGURATION:
    
        You should change the following configurations accordingly to your preferences.
     */
    public static final double GRAPH_VERTEX_DIAMETER = 20;
    public static final Color GRAPH_VERTEX_STROKE = Color.CORAL;
    public static final Color GRAPH_VERTEX_FILL = Color.ORANGE;
    public static final Font GRAPH_VERTEX_LABEL_FONT = Font.font("Verdana", FontWeight.BOLD, 18);

    public static final Color GRAPH_EDGE_COLOR = Color.FORESTGREEN.deriveColor(1, 1, 1, 0.4);
    public static final int GRAPH_EDGE_WIDTH = 8;
    public static final Font GRAPH_EDGE_LABEL_FONT = Font.font("Verdana", FontWeight.LIGHT, 10);

    public static final Color ARROW_COLOR = Color.BLUEVIOLET;

    public static final boolean GRAPH_VERTEX_USE_TOOLTIP = false;
    public static final boolean GRAPH_EDGE_USE_TOOLTIP = false;

    private static final double MAX_ANGLE_PLACEMENT = 20;

    /*
        END CONFIGURATION.
     */
    private final DiGraph<V, E> theGraph;
    private final VertexPlacementStrategy placementStrategy;
    private Map<Vertex<V>, GraphVertex> graphVertexMap;
    private Map<Edge<E, V>, GraphEdge> graphEdgeMap;

    public GraphPanel(DiGraph<V, E> theGraph, VertexPlacementStrategy placementStrategy) {
        if (theGraph == null) {
            throw new IllegalArgumentException("The graph cannot be null.");
        }
        if (placementStrategy == null) {
            throw new IllegalArgumentException("Placement strategy cannot be null");
        }

        this.theGraph = theGraph;
        this.placementStrategy = placementStrategy;

        graphVertexMap = new HashMap<>();
        graphEdgeMap = new HashMap<>();
    }

    /**
     * Resets vertices and edges to default colors. Default colors are defined in constants in this class.
     */
    public void resetColorsToDefault() {

        for (Vertex<V> vertex : graphVertexMap.keySet()) {
            GraphVertex graphVertex = graphVertexMap.get(vertex);
            graphVertex.setFill(GRAPH_VERTEX_FILL);
            graphVertex.setStroke(GRAPH_VERTEX_STROKE);
        }

        for (Edge<E, V> edge : graphEdgeMap.keySet()) {
            GraphEdge graphEdge = graphEdgeMap.get(edge);
            graphEdge.setFill(GRAPH_EDGE_COLOR);
        }

    }

    /**
     * Change a vertex's colors.
     *
     * @param v the graph vertex
     * @param fill color to fill the vertex shape
     * @param stroke color for the outline of the vertex shape
     * @return true if vertex was found and color changed; false otherwise.
     */
    public boolean setVertexColor(Vertex<V> v, Color fill, Color stroke) {
        GraphVertex graphVertex = graphVertexMap.get(v);
        if (graphVertex == null) {
            return false;
        }

        graphVertex.setFill(fill);
        graphVertex.setStroke(stroke);
        return true;
    }

    /**
     * Change an edge's color
     *
     * @param e the graph edge
     * @param c the color for the edge
     * @param opacity the opacity to be applied to the color <i>c</i> [0,1];
     * @return true if edge was found and color changed; false otherwise.
     */
    public boolean setEdgeColor(Edge<E, V> e, Color c, double opacity) {
        GraphEdge graphEdge = graphEdgeMap.get(e);
        if (graphEdge == null) {
            return false;
        }

        graphEdge.setStroke(c.deriveColor(1, 1, 1, opacity));
        return true;
    }

    /**
     * Method that effectively plots the graph.
     *
     * This method must be called AFTER an instance of this class was added to a JavaFX scene. It it is called before, then the initial values for width and height of this pane are zero, effectively causing all vertices to be placed in the top left corner. *
     */
    public void plotGraph() {
        if (this.getScene() == null) {
            throw new IllegalStateException("You must call this method after the instance was added to a scene.");
        }

        /* create vertex graphical representations */
        for (Vertex<V> vertex : listOfVertices()) {
            GraphVertex vertexAnchor = new GraphVertex(0, 0, GRAPH_VERTEX_DIAMETER);
            graphVertexMap.put(vertex, vertexAnchor);
        }

        /* call strategy to place the vertices in their initial locations */
        placementStrategy.placeVertices(this.widthProperty().doubleValue(),
                this.heightProperty().floatValue(), graphVertexMap);

        /* create edges graphical representations between existing vertices */
        //this is used to guarantee that no duplicate edges are ever inserted
        List<Edge<E, V>> edgesToPlace = listOfEdges();

        for (Vertex<V> vertex : graphVertexMap.keySet()) {

            Iterable<Edge<E, V>> incidentEdges = theGraph.outboundEdges(vertex);

            for (Edge<E, V> edge : incidentEdges) {

                //if already plotted, ignore edge.
                if (!edgesToPlace.contains(edge)) {
                    continue;
                }

                /* (begin) TODO: You may have to adapt this code for other Graph variants. */
                Vertex<V> oppositeVertex = theGraph.opposite(vertex, edge);

                if (oppositeVertex == null) {
                    continue;
                }

                GraphVertex graphVertex = graphVertexMap.get(vertex);
                GraphVertex graphVertexOpposite = graphVertexMap.get(oppositeVertex);
                /* (end) TODO: */

                /**
                 * The following code is generic enough for all situations, so you can leave it the way it is.
                 */
                double midpointX = (graphVertex.getCenterX() + graphVertexOpposite.getCenterX()) / 2;
                double midpointY = (graphVertex.getCenterY() + graphVertexOpposite.getCenterY()) / 2;
                Point2D midpoint = new Point2D(midpointX, midpointY);

                Point2D startpoint = new Point2D(graphVertex.getCenterX(), graphVertex.getCenterY());

                if (getTotalEdgesBetween(vertex, oppositeVertex) > 1) {
                    //random rotation between [-45,45] degrees
                    double angle = MAX_ANGLE_PLACEMENT;
                    midpoint = UtilitiesPoint2D.rotate(midpoint,
                            startpoint,
                            (-angle) + Math.random() * (angle - (-angle)));
                }

                GraphEdge graphEdge = new GraphEdge();
                graphEdge.startXProperty().bind(graphVertex.centerXProperty());
                graphEdge.startYProperty().bind(graphVertex.centerYProperty());

                graphEdge.setControlX1(midpoint.getX());
                graphEdge.setControlY1(midpoint.getY());
                graphEdge.setControlX2(midpoint.getX());
                graphEdge.setControlY2(midpoint.getY());

                graphEdge.endXProperty().bind(graphVertexOpposite.centerXProperty());
                graphEdge.endYProperty().bind(graphVertexOpposite.centerYProperty());

                graphEdge.setStroke(GRAPH_EDGE_COLOR);
                graphEdge.setStrokeWidth(GRAPH_EDGE_WIDTH);

                graphEdge.setFill(Color.TRANSPARENT);

                this.getChildren().add(graphEdge);
                graphEdgeMap.put(edge, graphEdge);

                if (GRAPH_VERTEX_USE_TOOLTIP) {
                    Tooltip t = new Tooltip(edge.element().toString());
                    Tooltip.install(graphEdge, t);
                } else {
                    Text label = new Text(edge.element().toString());
                    label.setFont(GRAPH_EDGE_LABEL_FONT);

                    label.xProperty().bind(graphEdge.controlX1Property().add(graphEdge.controlX1Property()).divide(2).subtract(label.getLayoutBounds().getWidth() / 2));
                    label.yProperty().bind(graphEdge.controlY1Property().add(graphEdge.controlY1Property()).divide(2));

                    this.getChildren().add(label);
                }
                /* 
                (begin) TODO:
                Place arrow on edge. Remove or adapt according to your requirements.
                
                if param t < 0.5 : Direction is from graphVertex -> graphVertexOpposite 
                else in the other direction
                 */
                //double[] arrowShape = new double[]{0, 0, 10, 20, -10, 20};
                double[] arrowShape = new double[]{0, 0, GRAPH_EDGE_WIDTH, 2 * GRAPH_EDGE_WIDTH, -GRAPH_EDGE_WIDTH, 2 * GRAPH_EDGE_WIDTH};

                //counts the times the edge was Inserted, if this is the 2nd time, we dont insert it, we put an arrow on the already insterted one
                boolean inserted = false;
                Edge<E,V> insertedEdge = null;
                
                for(Edge<E, V> e : graphEdgeMap.keySet()) {
                    if(e.vertices()[0] == edge.vertices()[1] && e.vertices()[1] == edge.vertices()[0]) {
                        inserted = true;
                            insertedEdge = e;
                    }
                }
                
                if (!inserted) {
                    Arrow arrow2 = new Arrow(graphEdge, ARROW_COLOR, 0.8f, arrowShape);
                    graphEdge.addArrow(arrow2);
                    this.getChildren().add(arrow2);
                } else {
                    GraphEdge containedEdge = graphEdgeMap.get(insertedEdge);
                    Arrow arrow1 = new Arrow(containedEdge, ARROW_COLOR, 0.2f, arrowShape);
                    containedEdge.addArrow(arrow1);
                    this.getChildren().add(arrow1);
                    this.getChildren().remove(graphEdge);
                    graphEdgeMap.remove(insertedEdge);
                }
                /* (end) TODO: */

 /* Edge already placed. */
                edgesToPlace.remove(edge);
            }

        }

        /* place anchors above lines */
        for (Vertex<V> vertex : graphVertexMap.keySet()) {
            GraphVertex anchor = graphVertexMap.get(vertex);

            /* Style for the vertex representation */
            anchor.setFill(GRAPH_VERTEX_FILL);
            anchor.setStroke(GRAPH_VERTEX_STROKE);
            anchor.setStrokeWidth(4);
            anchor.setStrokeType(StrokeType.OUTSIDE);

            this.getChildren().add(anchor);

            if (GRAPH_VERTEX_USE_TOOLTIP) {
                Tooltip t = new Tooltip(vertex.element().toString());
                Tooltip.install(anchor, t);
            } else {
                Text label = new Text(vertex.element().toString());
                label.setFont(GRAPH_VERTEX_LABEL_FONT);

                label.xProperty().bind(anchor.centerXProperty().add(anchor.centerXProperty()).divide(2).subtract(label.getLayoutBounds().getWidth() / 2));
                label.yProperty().bind(anchor.centerYProperty().add(anchor.centerYProperty()).divide(2).add(GRAPH_VERTEX_DIAMETER * 2));

                this.getChildren().add(label);
            }

        }

        enableReziseListener();
    }

    /**
     * TODO: It may be necessary to adjust this method based, if you use another Graph variant done?
     */
    private int getTotalEdgesBetween(Vertex<V> v, Vertex<V> u) {

        int count = 0;
        for (Edge<E, V> edge : theGraph.edges()) {
            if (edge.vertices()[0] == v && edge.vertices()[1] == u) {
                count++;
            }
        }
        return count;
    }

    private List<Edge<E, V>> listOfEdges() {
        List<Edge<E, V>> list = new LinkedList<>();
        for (Edge<E, V> edge : theGraph.edges()) {
            list.add(edge);
        }
        return list;
    }

    private List<Vertex<V>> listOfVertices() {
        List<Vertex<V>> list = new LinkedList<>();
        for (Vertex<V> vertex : theGraph.vertices()) {
            list.add(vertex);
        }
        return list;
    }

    /**
     * Change the following code only if you know what you are doing.
     */
    private void enableReziseListener() {

        Scene scene = this.getScene();
        final double initWidth = scene.getWidth();
        final double initHeight = scene.getHeight();
        final double ratio = initWidth / initHeight;

        SceneSizeChangeListener sizeListener = new SceneSizeChangeListener(this, ratio, initHeight, initWidth, this);
        scene.widthProperty().addListener(sizeListener);
        scene.heightProperty().addListener(sizeListener);
    }

    private static class SceneSizeChangeListener implements ChangeListener<Number> {

        private final Scene scene;
        private final double ratio;
        private final double initHeight;
        private final double initWidth;
        private final Pane contentPane;

        public SceneSizeChangeListener(Node rootNode, double ratio, double initHeight, double initWidth, Pane contentPane) {
            this.scene = rootNode.getScene();
            this.ratio = ratio;
            this.initHeight = initHeight;
            this.initWidth = initWidth;
            this.contentPane = contentPane;
        }

        @Override
        public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {

            final double newWidth = scene.getWidth();
            final double newHeight = scene.getHeight();

            double scaleFactor
                    = newWidth / newHeight > ratio
                            ? newHeight / initHeight
                            : newWidth / initWidth;

            if (scaleFactor >= 1) {
                Scale scale = new Scale(scaleFactor, scaleFactor);
                scale.setPivotX(0);
                scale.setPivotY(0);
                scene.getRoot().getTransforms().setAll(scale);

                contentPane.setPrefWidth(newWidth / scaleFactor);
                contentPane.setPrefHeight(newHeight / scaleFactor);
            } else {
                contentPane.setPrefWidth(Math.max(initWidth, newWidth));
                contentPane.setPrefHeight(Math.max(initHeight, newHeight));
            }
        }
    }

}
