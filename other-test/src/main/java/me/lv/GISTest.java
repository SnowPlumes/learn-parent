package me.lv;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zerozero
 * @date 2022/9/9
 */
public class GISTest extends JComponent {

    public static void main(String[] args) {
        draw();
    }

    public static void draw() {
        //点在多边形内
//        Point2D.Double point = new Point2D.Double(133.88168,34.877413);
        //点在多边形外
//        Point2D.Double point = new Point2D.Double(138.320156,35.951818);

//        System.out.println(contains(getPolygon(), point));

        JFrame frame = new JFrame("Draw GeneralPath Demo");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(new GISTest());
        frame.pack();
        frame.setSize(new Dimension(900, 900));
        frame.setVisible(true);
    }



    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setStroke(new BasicStroke(3.0f));
        g2.setPaint(Color.GREEN);
        g2.translate(0, getHeight());
        g2.scale(1, -1);

        for (List<Point2D.Double> polygon : getPolygon3()) {
            List<Point2D.Double> points = polygon.stream().map(aDouble -> new Point2D.Double((aDouble.x - 60) * 10, aDouble.y * 10)).collect(Collectors.toList());
            GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD, points.size());

            Point2D.Double first = points.get(0);
            path.moveTo(first.x, first.y);

            for(Point2D.Double d : points){
                path.lineTo(d.x, d.y);
            }

            path.lineTo(first.x, first.y);
            path.closePath();
            g2.draw(path);
        }

    }

    private static void draw4(Graphics2D g2) {
        List<Point2D.Double> points = getPolygon4().stream().map(aDouble -> new Point2D.Double((aDouble.x - 60) * 10, aDouble.y * 10)).collect(Collectors.toList());
        GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD, points.size());

        Point2D.Double first = points.get(0);
        path.moveTo(first.x, first.y);

        for(Point2D.Double d : points){
            path.lineTo(d.x, d.y);
        }

        path.lineTo(first.x, first.y);
        path.closePath();
        g2.draw(path);
    }

    public static List<Point2D.Double> getPolygon4() {
        String points = "[[120.38686916,24.11828036],[120.47851946,24.26705246],[120.5781891,24.42508175],[120.60608767,24.54200552],[120.73154222,24.62515909],[120.87007004,24.72164111],[120.96046384,25.00365368],[121.02534733,25.06666599],[121.12715228,25.06936097],[121.31872904,25.12477336],[121.43902721,25.20837202],[121.47419793,25.24514175],[121.62405563,25.35490535],[121.6847393,25.24457527],[121.70963743,25.23936103],[121.80750437,25.15813141],[121.94627136,25.04524812],[121.9611445,24.98041966],[121.86307448,24.83915203],[121.85712523,24.73956879],[121.88422103,24.44200576],[121.77239518,24.21125212],[121.69050893,24.07320029],[121.67894844,23.94752959],[121.54553482,23.64091111],[121.53982338,23.45170452],[121.46495025,23.24343329],[121.37862388,23.00777072],[121.27902742,22.81467463],[121.14943133,22.69680854],[120.97441486,22.53688825],[120.9507075,22.35074111],[120.93102806,22.11469985],[120.89550296,21.93834123],[120.76410859,21.83059939],[120.6810886,21.97301134],[120.65075244,22.12813785],[120.57327743,22.29084936],[120.44827932,22.44718233],[120.33973352,22.5247785],[120.21485914,22.66159753],[120.11083067,22.8909247],[120.10130959,22.97059531],[120.00043803,23.06605163],[120.08400115,23.21640946],[120.08729688,23.41915895],[120.08729661,23.57097655],[120.11679938,23.71405489],[120.16328224,23.87745067],[120.28481266,24.00494268],[120.32016895,24.06551781],[120.38686916,24.11828036]]";
        JSONArray jsonArray = JSON.parseArray(points);
        List<Point2D.Double> polygon = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONArray areas = jsonArray.getJSONArray(i);
            polygon.add(new Point2D.Double(areas.getDouble(0), areas.getDouble(1)));
        }
        return polygon;
    }

    public static List<List<Point2D.Double>> getPolygon3() {
        List<List<Point2D.Double>> result = new ArrayList<>();
        String s = HttpUtil.get("https://geo.datav.aliyun.com/areas_v3/bound/geojson?code=100000");
        JSONObject parse = JSON.parseObject(s);
        JSONArray jsonArray = parse.getJSONArray("features").getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates");
        for (int i = 0; i < jsonArray.size(); i++) {
            List<Point2D.Double> polygon = new ArrayList<>();
            JSONArray areas = jsonArray.getJSONArray(i).getJSONArray(0);
            for (int j = 0; j < areas.size(); j++) {
                JSONArray area = areas.getJSONArray(j);
                polygon.add(new Point2D.Double(area.getDouble(0), area.getDouble(1)));
            }
            result.add(polygon);
        }
        return result;
    }

    public static List<Point2D.Double> getPolygon2() {
        List<Point2D.Double> polygon = new ArrayList<>();
        polygon.add(new Point2D.Double(136.232754,36.209309));
        polygon.add(new Point2D.Double(137.199551,37.003034));
        polygon.add(new Point2D.Double(138.583828,37.510204));
        polygon.add(new Point2D.Double(139.418789,38.238653));
        polygon.add(new Point2D.Double(139.836269,39.181642));
        polygon.add(new Point2D.Double(139.880215,40.447406));
        polygon.add(new Point2D.Double(141.022793,40.947169));
        polygon.add(new Point2D.Double(141.791836,40.246451));
        polygon.add(new Point2D.Double(142.099453,39.419686));
        polygon.add(new Point2D.Double(141.594082,38.582997));
        polygon.add(new Point2D.Double(141.066738,37.719067));
        polygon.add(new Point2D.Double(140.627285,36.492458));
        polygon.add(new Point2D.Double(140.473477,35.281993));
        polygon.add(new Point2D.Double(139.133144,34.742108));
        polygon.add(new Point2D.Double(137.682949,34.687923));
        polygon.add(new Point2D.Double(137.682949,34.687923));
        polygon.add(new Point2D.Double(136.408535,35.335785));

        return polygon;
    }


    public static List<Point2D.Double> getPolygon() {
        List<Point2D.Double> polygon = new ArrayList<>();
        polygon.add(new Point2D.Double(131.036221,34.398342));
        polygon.add(new Point2D.Double(131.387783,34.525158));
        polygon.add(new Point2D.Double(131.651455,34.705989));
        polygon.add(new Point2D.Double(131.9371,34.85037));
        polygon.add(new Point2D.Double(132.310635,35.120401));
        polygon.add(new Point2D.Double(132.640225,35.371626));
        polygon.add(new Point2D.Double(133.01376,35.604209));
        polygon.add(new Point2D.Double(133.321377,35.532717));
        polygon.add(new Point2D.Double(133.848721,35.514834));
        polygon.add(new Point2D.Double(134.288174,35.622072));
        polygon.add(new Point2D.Double(134.771572,35.711327));
        polygon.add(new Point2D.Double(135.189053,35.764832));
        polygon.add(new Point2D.Double(135.540615,35.514834));
        polygon.add(new Point2D.Double(136.045986,35.228165));
        polygon.add(new Point2D.Double(136.661221,34.615623));
        polygon.add(new Point2D.Double(136.661221,34.615623));
        polygon.add(new Point2D.Double(136.90292,34.180496));
        polygon.add(new Point2D.Double(136.419521,34.08956));
        polygon.add(new Point2D.Double(136.002041,33.505262));
        polygon.add(new Point2D.Double(135.276943,33.669998));
        polygon.add(new Point2D.Double(134.617764,33.724841));
        polygon.add(new Point2D.Double(134.266201,33.30349));
        polygon.add(new Point2D.Double(133.716885,33.486938));
        polygon.add(new Point2D.Double(133.277432,33.027593));
        polygon.add(new Point2D.Double(132.969814,32.732348));
        polygon.add(new Point2D.Double(132.464443,33.046013));
        polygon.add(new Point2D.Double(132.112881,33.688283));
        polygon.add(new Point2D.Double(131.541592,34.053159));
        polygon.add(new Point2D.Double(131.244961,33.94386));
        polygon.add(new Point2D.Double(131.014248,34.080461));
        polygon.add(new Point2D.Double(130.849453,34.180496));
        return polygon;
    }

    /**
     * 判断点是否在多边形内
     * 步骤：
     * ①声明一个“画笔”
     * ②将“画笔”移动到多边形的第一个顶点
     * ③用“画笔”按顺序将多边形的顶点连接起来
     * ④用“画笔”将多边形的第一个点连起来，最终形成一个封闭的多边形
     * ⑤用contains()方法判断点是否在多边形区域内
     * @param polygon 多边形
     * @param point 检测点
     * @return 点在多边形内返回true，否则返回false
     */
    public static boolean contains(List<Point2D.Double> polygon, Point2D.Double point){

        GeneralPath p = new GeneralPath();

        Point2D.Double first = polygon.get(0);
        p.moveTo(first.x, first.y);

        for(Point2D.Double d : polygon){
            p.lineTo(d.x, d.y);
        }

        p.lineTo(first.x, first.y);
        p.closePath();
        return p.contains(point);
    }

}
