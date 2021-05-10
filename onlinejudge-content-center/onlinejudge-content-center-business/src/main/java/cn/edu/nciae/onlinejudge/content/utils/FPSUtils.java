package cn.edu.nciae.onlinejudge.content.utils;

import cn.edu.nciae.onlinejudge.content.domain.Checkpoint;
import cn.edu.nciae.onlinejudge.content.domain.Sample;
import cn.edu.nciae.onlinejudge.content.vo.ProblemDTO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/20 11:03
 */

public class FPSUtils {

    private static NodeList itemList;

    /**
     * 将fps转换成题目集
     * @param userId 用户id
     * @param filepath
     * @return
     */
    public static List<ProblemDTO> fps2ProblemVO(Long userId, String filepath) {
        Document doc;
        doc = parseXML(filepath);
        List<ProblemDTO> problems = new ArrayList<ProblemDTO>();
        //找到item标签
        itemList = doc.getElementsByTagName("item");
        for (int i = 0; i < itemList.getLength(); i++) {
            // 将item转换成ProblemVO
            ProblemDTO problemDTO = itemToProblemVO(itemList.item(i));
            //设置添加者id
            problemDTO.setAddUserId(userId);
            problems.add(problemDTO);
        }
        return problems;
    }

    /**
     * 将item转换成ProblemVO
     *
     * @param item
     * @return
     */
    private static ProblemDTO itemToProblemVO(Node item) {
        ProblemDTO problemDTO = new ProblemDTO();
        //获取输入输出样例
        List<Sample> sampleList = getSampleList(item);
        //获取测试用例
        List<Checkpoint> checkpointList = getCheckpointList(item);
        NodeList nodeList = item.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            String name = node.getNodeName();
            String value = node.getTextContent();
            if ("problem_title".equalsIgnoreCase(name)) {
                problemDTO.setProblemTitle(value);
            }
            if ("problem_time_limit".equalsIgnoreCase(name)) {
                Element tmp = (Element) node;
                String unit = tmp.getAttribute("unit");
                if ("s".equalsIgnoreCase(unit)) {
                    problemDTO.setProblemTimeLimit(Integer.parseInt(String.valueOf(value.split(" ")[0])) * 1000);
                } else {
                    problemDTO.setProblemTimeLimit(Integer.valueOf(String.valueOf(value.split(" ")[0])));
                }
            }
            if ("problem_memory_limit".equalsIgnoreCase(name)) {
                Element tmp = (Element) node;
                String unit = tmp.getAttribute("unit");
                if ("b".equalsIgnoreCase(unit)) {
                    problemDTO.setProblemMemoryLimit(Integer.parseInt(String.valueOf(value.split(" ")[0])) / 1024 /1024);
                } else {
                    problemDTO.setProblemMemoryLimit(Integer.valueOf(String.valueOf(value.split(" ")[0])));
                }
            }
            if ("problem_description".equalsIgnoreCase(name)) {
                problemDTO.setProblemDescription(value);
            }
            if ("problem_input_formation".equalsIgnoreCase(name)) {
                problemDTO.setProblemInputFormation(value);
            }
            if ("problem_output_formation".equalsIgnoreCase(name)) {
                problemDTO.setProblemOutputFormation(value);
            }
            if ("problem_reminder".equalsIgnoreCase(name)) {
                problemDTO.setProblemReminder(value);
            }
            if ("problem_author".equalsIgnoreCase(name)) {
                problemDTO.setProblemAuthor(value);
            }
        }
        problemDTO.setProblemSubmitNumber(0);
        problemDTO.setProblemSolvedNumber(0);
        problemDTO.setSamples(sampleList);
        problemDTO.setCheckpoints(checkpointList);
        return problemDTO;
    }

    /**
     * 解析xml文件
     *
     * @param filepath
     * @return
     */
    private static Document parseXML(String filepath) {
        Document doc = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(filepath);
            doc.normalize();

        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return doc;
    }

    /**
     * 获取输入输出样例
     *
     * @param item
     * @return
     */
    public static List<Sample> getSampleList(Node item) {
        List<Sample> sampleList = new ArrayList<>(5);
        //获取输入输出样例标签
        NodeList sampleInputTags = ((Element) item).getElementsByTagName("sample_input");
        NodeList sampleOutputTags = ((Element) item).getElementsByTagName("sample_output");
        for (int i = 0; i < sampleInputTags.getLength(); i++) {
            Element elementIn = (Element) sampleInputTags.item(i);
            Element elementOut = (Element) sampleOutputTags.item(i);
            Sample sample = Sample.builder()
                    .sampleInput(elementIn.getTextContent())
                    .sampleOutput(elementOut.getTextContent())
                    .build();
            sampleList.add(sample);
        }
        return sampleList;
    }

    /**
     * 获取测试用例
     *
     * @param item
     * @return
     */
    public static List<Checkpoint> getCheckpointList(Node item) {
        List<Checkpoint> checkpointList = new ArrayList<>(20);
        //获取输入输出样例标签
        NodeList checkpointInputTags = ((Element) item).getElementsByTagName("test_input");
        NodeList checkpointOutputTags = ((Element) item).getElementsByTagName("test_output");
        for (int i = 0; i < checkpointInputTags.getLength(); i++) {
            Element elementIn = (Element) checkpointInputTags.item(i);
            Element elementOut = (Element) checkpointOutputTags.item(i);
            Checkpoint checkpoint = Checkpoint.builder()
                    .input(elementIn.getTextContent())
                    .output(elementOut.getTextContent())
                    .build();
            checkpointList.add(checkpoint);
        }
        return checkpointList;
    }
}
