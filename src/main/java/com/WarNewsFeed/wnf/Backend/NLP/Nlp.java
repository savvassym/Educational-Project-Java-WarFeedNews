package com.WarNewsFeed.wnf.Backend.NLP;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Nlp {

    private Map<String,String> collect = new HashMap<>();


    public Map<String, String> analyzer(String input){
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        Annotation document = new Annotation(input);
        pipeline.annotate(document);
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        for(CoreMap sentence : sentences){
            for(CoreLabel tokens : sentence.get(CoreAnnotations.TokensAnnotation.class)){
                collect.put(tokens.get(CoreAnnotations.TextAnnotation.class), tokens.get(CoreAnnotations.NamedEntityTagAnnotation.class));
            }
        }
        return collect;
    }
}
