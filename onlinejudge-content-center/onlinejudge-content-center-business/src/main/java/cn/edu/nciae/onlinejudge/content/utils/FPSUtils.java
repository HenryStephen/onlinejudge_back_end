package cn.edu.nciae.onlinejudge.content.utils;

import cn.edu.nciae.onlinejudge.content.domain.Checkpoint;
import cn.edu.nciae.onlinejudge.content.domain.Sample;
import cn.edu.nciae.onlinejudge.content.domain.Tag;
import cn.edu.nciae.onlinejudge.content.vo.ProblemDTO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
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
     * @param userName 题目作者
     * @param userId 用户id
     * @param file
     * @return
     */
    public static List<ProblemDTO> fps2ProblemVO(String userName, Long userId, File file) {
        Document doc;
        doc = parseFile(file);
        List<ProblemDTO> problems = new ArrayList<ProblemDTO>();
        //找到item标签
        itemList = doc.getElementsByTagName("item");
        for (int i = 0; i < itemList.getLength(); i++) {
            // 将item转换成ProblemVO
            ProblemDTO problemDTO = itemToProblemVO(itemList.item(i));
            //设置添加者id
            problemDTO.setAddUserId(userId);
            //设置作者
            problemDTO.setProblemAuthor(userName);
            problems.add(problemDTO);
        }
        return problems;
    }

    /**
     * 将item转换成ProblemVO
     * @param item
     * @return
     */
    private static ProblemDTO itemToProblemVO(Node item) {
        ProblemDTO problemDTO = new ProblemDTO();
        //获取输入输出样例
        List<Sample> sampleList = getSampleList(item);
        //获取测试用例
        List<Checkpoint> checkpointList = getCheckpointList(item);
        //获取标签列表
        List<Tag> tagList = getTagList(item);
        //获取题目支持编程语言列表
        List<String> languageList = getLanguageList(item);
        NodeList nodeList = item.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            String name = node.getNodeName();
            String value = node.getTextContent();
            if ("title".equalsIgnoreCase(name)) {
                problemDTO.setProblemTitle(value);
            }
            if ("time_limit".equalsIgnoreCase(name)) {
                Element tmp = (Element) node;
                String unit = tmp.getAttribute("unit");
                if ("s".equalsIgnoreCase(unit)) {
                    problemDTO.setProblemTimeLimit(Integer.parseInt(String.valueOf(value.split(" ")[0])) * 1000);
                } else {
                    problemDTO.setProblemTimeLimit(Integer.valueOf(String.valueOf(value.split(" ")[0])));
                }
            }
            if ("memory_limit".equalsIgnoreCase(name)) {
                Element tmp = (Element) node;
                String unit = tmp.getAttribute("unit");
                if ("b".equalsIgnoreCase(unit)) {
                    problemDTO.setProblemMemoryLimit(Integer.parseInt(String.valueOf(value.split(" ")[0])) / 1024 /1024);
                } else {
                    problemDTO.setProblemMemoryLimit(Integer.valueOf(String.valueOf(value.split(" ")[0])));
                }
            }
            if ("description".equalsIgnoreCase(name)) {
                problemDTO.setProblemDescription(value);
            }
            if ("input".equalsIgnoreCase(name)) {
                problemDTO.setProblemInputFormation(value);
            }
            if ("output".equalsIgnoreCase(name)) {
                problemDTO.setProblemOutputFormation(value);
            }
            if ("hint".equalsIgnoreCase(name)) {
                problemDTO.setProblemReminder(value);
            }
            if ("source".equalsIgnoreCase(name)) {
                problemDTO.setProblemAuthor(value);
            }
            if ("difficulty".equalsIgnoreCase(name)) {
                problemDTO.setProblemDifficulty(value);
            }
        }
        problemDTO.setSamples(sampleList);
        problemDTO.setLanguages(languageList);
        problemDTO.setTags(tagList);
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
     * 解析文件
     * @param file
     * @return
     */
    private static Document parseFile(File file){
        try{
            // 初始化xml解析工厂
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // 创建DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();
            // 创建解析xml的Document
            Document doc = (Document) builder.parse(file);
            return doc;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (SAXException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取输入输出样例
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
     * 获取题目标签列表
     * @param item
     * @return
     */
    public static List<Tag> getTagList(Node item){
        List<Tag> tagList = new ArrayList<>(5);
        NodeList tags = ((Element) item).getElementsByTagName("tag");
        for(int i=0;i<tags.getLength();i++){
            Element eleTag = (Element) tags.item(i);
            Tag tag = new Tag();
            tag.setTagName(eleTag.getTextContent());
            tagList.add(tag);
        }
        return tagList;
    }

    /**
     * 获取题目支持编程语言列表
     * @param item
     * @return
     */
    public static List<String> getLanguageList(Node item){
        List<String> languagesList = new ArrayList<>(10);
        NodeList languageTags = ((Element) item).getElementsByTagName("language");
        for(int i=0;i<languageTags.getLength();i++){
            Element eleLanguage = (Element) languageTags.item(i);
            languagesList.add(eleLanguage.getTextContent());
        }
        return languagesList;
    }

    /**
     * 获取测试用例
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
