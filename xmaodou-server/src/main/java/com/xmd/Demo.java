package com.xmd;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/12/9 下午5:23
 */
public class Demo {


    public static void main(String[] args) {
        String str = "[\n" +
                "    {\n" +
                "        \"content_type\": \"application/msword\",\n" +
                "        \"create_time\": \"2021-12-09 17:20:55\",\n" +
                "        \"file_name\": [\n" +
                "            \"左亮同志考察材料.doc\",\n" +
                "            \"左亮同志考察材料__(1).doc\"\n" +
                "        ],\n" +
                "        \"file_size_bytes\": 83,\n" +
                "        \"result\": [\n" +
                "            [\n" +
                "                {\n" +
                "                    \"advantage\": [\n" +
                "                        \"在新冠疫情防控期间，带领街道干部累计入户排查6万余户次，16万余人次，建立重点对象台账1943人\"\n" +
                "                    ],\n" +
                "                    \"advantageScore\": 0.9928,\n" +
                "                    \"evaluate\": [\n" +
                "                        {\n" +
                "                            \"level\": 3.7569399059459463,\n" +
                "                            \"text\": \"攻坚能力\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"level\": 3.2726805899189184,\n" +
                "                            \"text\": \"政治素质\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"level\": 3.1088601024864864,\n" +
                "                            \"text\": \"服从组织\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"level\": 3.0637103498648655,\n" +
                "                            \"text\": \"立场坚定\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"level\": 2.8869734667837834,\n" +
                "                            \"text\": \"廉洁自律\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"level\": 2.650360294527027,\n" +
                "                            \"text\": \"兢兢业业\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"level\": 2.437263710645946,\n" +
                "                            \"text\": \"经验丰富\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"name\": \"左亮\",\n" +
                "                    \"shortcoming\": [\n" +
                "                        \"理论知识学习有待加强，党建工作投入不够\"\n" +
                "                    ],\n" +
                "                    \"shortcomingScore\": 0.0072\n" +
                "                }\n" +
                "            ],\n" +
                "            [\n" +
                "                {\n" +
                "                    \"advantage\": [\n" +
                "                        \"在新冠疫情防控期间，带领街道干部累计入户排查6万余户次，16万余人次，建立重点对象台账1943人\"\n" +
                "                    ],\n" +
                "                    \"advantageScore\": 0.9928,\n" +
                "                    \"evaluate\": [\n" +
                "                        {\n" +
                "                            \"level\": 3.7569399059459463,\n" +
                "                            \"text\": \"攻坚能力\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"level\": 3.2726805899189184,\n" +
                "                            \"text\": \"政治素质\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"level\": 3.1088601024864864,\n" +
                "                            \"text\": \"服从组织\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"level\": 3.0637103498648655,\n" +
                "                            \"text\": \"立场坚定\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"level\": 2.8869734667837834,\n" +
                "                            \"text\": \"廉洁自律\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"level\": 2.650360294527027,\n" +
                "                            \"text\": \"兢兢业业\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"level\": 2.437263710645946,\n" +
                "                            \"text\": \"经验丰富\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"level\": 3.7569399059459463,\n" +
                "                            \"text\": \"攻坚能力\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"level\": 3.2726805899189184,\n" +
                "                            \"text\": \"政治素质\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"level\": 3.1088601024864864,\n" +
                "                            \"text\": \"服从组织\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"level\": 3.0637103498648655,\n" +
                "                            \"text\": \"立场坚定\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"level\": 2.8869734667837834,\n" +
                "                            \"text\": \"廉洁自律\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"level\": 2.650360294527027,\n" +
                "                            \"text\": \"兢兢业业\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"level\": 2.437263710645946,\n" +
                "                            \"text\": \"经验丰富\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"name\": \"左亮\",\n" +
                "                    \"shortcoming\": [\n" +
                "                        \"理论知识学习有待加强，党建工作投入不够\"\n" +
                "                    ],\n" +
                "                    \"shortcomingScore\": 0.0072\n" +
                "                }\n" +
                "            ]\n" +
                "        ],\n" +
                "        \"status\": 200\n" +
                "    }\n" +
                "]";

        List<JSONArray> records = JsonPath.parse(str).read( "$[0].result", List.class);
        for (JSONArray record : records) {
            for (Object map : record) {
                LinkedHashMap m = (LinkedHashMap) map;
                JSONArray advantage = (JSONArray) m.get("advantage");
                Double advantageScore = (Double) m.get("advantageScore");
                JSONArray evaluate = (JSONArray) m.get("evaluate");
                int size = evaluate.size();
                for (Object e : evaluate) {
                    LinkedHashMap linkedHashMap = (LinkedHashMap) e;


                }
                String name = (String) m.get("name");
                JSONArray shortcoming = (JSONArray) m.get("shortcoming");
                Double shortcomingScore = (Double) m.get("shortcomingScore");
                System.out.println();
            }
        }


        System.out.println();


    }
}
