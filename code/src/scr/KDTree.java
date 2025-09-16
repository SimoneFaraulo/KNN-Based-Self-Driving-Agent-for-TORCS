package scr;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class KDTree {

    private KDNode root;

    public KDTree(List<Sample> points) {
        root = buildTree(points, 0);
    }

    private static class KDNode {
        Sample point;
        KDNode left, right;

        KDNode(Sample point) {
            this.point = point;
        }
    }

    private KDNode buildTree(List<Sample> points, int depth) {
        if (points.isEmpty()) {
            return null;
        }

        int axis = depth % 12; // Assuming 3-dimensional space (x, y, z)
        points.sort(Comparator.comparingDouble(p -> getAxisValue(p, axis)));

        int medianIndex = points.size() / 2;
        KDNode node = new KDNode(points.get(medianIndex));

        node.left = buildTree(points.subList(0, medianIndex), depth + 1);
        node.right = buildTree(points.subList(medianIndex + 1, points.size()), depth + 1);

        return node;
    }

    //Metodo per ottenere il valore di una specifica dimensione di un punto
    private double getAxisValue(Sample sample, int axis) {
        switch (axis) {
            case 0:
                return sample.angle;
            case 1:
                return sample.speedX;
            case 2:
                return sample.speedY;
            case 3:
                return sample.track[0];
            case 4:
                return sample.track[3];
            case 5:
                return sample.track[7];
            case 6:
                return sample.track[8];
            case 7:
                return sample.track[10];
            case 8:
                return sample.track[12];
            case 9:
                return sample.track[15];
            case 10:
                return sample.track[18];
            case 11:
                return sample.track_pos;
            default:
                throw new IllegalArgumentException("Invalid axis for Sample dimensions.");
        }
    }

    public List<Sample> kNearestNeighbors(Sample target, int k) {
        PriorityQueue<Sample> pq = new PriorityQueue<>(k, Comparator.comparingDouble(target::distance).reversed());
        kNearestNeighbors(root, target, k, 0, pq);
        return new ArrayList<>(pq);
    }

    private void kNearestNeighbors(KDNode node, Sample target, int k, int depth, PriorityQueue<Sample> pq) {
        if (node == null) {
            return;
        }

        double distance = target.distance(node.point);
        if (pq.size() < k) {
            pq.offer(node.point);
        } else if (distance < target.distance(pq.peek())) {
            pq.poll();
            pq.offer(node.point);
        }

        int axis = depth % 12;
        KDNode nearNode = getNearNode(node, target, axis);
        KDNode farNode = (nearNode == node.left) ? node.right : node.left;

        kNearestNeighbors(nearNode, target, k, depth + 1, pq);

        if (pq.size() < k || Math.abs(getAxisValue(target, axis) - getAxisValue(node.point, axis)) < target.distance(pq.peek())) {
            kNearestNeighbors(farNode, target, k, depth + 1, pq);
        }
    }

    //Metodo per determinare quale nodo figlio è più vicino al punto target lungo un asse specifico
    private KDNode getNearNode(KDNode node, Sample target, int axis) {
        if (axis == 0) {
            return (target.angle < node.point.angle) ? node.left : node.right;
        } else if (axis == 1) {
            return (target.speedX < node.point.speedX) ? node.left : node.right;
        } else if (axis == 2){ 
            return (target.speedY < node.point.speedY) ? node.left : node.right;
        } else if (axis == 3) {
            return (target.track[0] < node.point.track[0]) ? node.left : node.right;
        } else if (axis == 4) {
            return (target.track[3] < node.point.track[3]) ? node.left : node.right;
        } else if (axis == 5) {
            return (target.track[7] < node.point.track[7]) ? node.left : node.right;
        } else if (axis == 6) {
            return (target.track[8] < node.point.track[8]) ? node.left : node.right;
        } else if (axis == 7) {
            return (target.track[10] < node.point.track[10]) ? node.left : node.right;
        } else if (axis == 8) {
            return (target.track[12] < node.point.track[12]) ? node.left : node.right;
        } else if (axis == 9) {
            return (target.track[15] < node.point.track[15]) ? node.left : node.right;
        } else if (axis == 10) {
            return (target.track[18] < node.point.track[18]) ? node.left : node.right;
        } else if (axis == 11) {
            return (target.track_pos < node.point.track_pos) ? node.left : node.right;
        }
        
        
        else {
            throw new IllegalArgumentException("Invalid axis for Sample dimensions.");
        }
    }
}