package com.WarNewsFeed.wnf.backend.nlp;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Nlp {

    private Map<String,String> collect = new HashMap<>();
    private StanfordCoreNLP pip = propInit();
    public Map<String,String> analyzer(String input){
        Annotation document = new Annotation(input);
        pip.annotate(document);
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        for(CoreMap sentence : sentences){
            for(CoreLabel tokens : sentence.get(CoreAnnotations.TokensAnnotation.class)){
                //collect.put(tokens.get(CoreAnnotations.TextAnnotation.class), tokens.get(CoreAnnotations.NamedEntityTagAnnotation.class));
                collect.put(tokens.get(CoreAnnotations.TextAnnotation.class),tokens.get(CoreAnnotations.NamedEntityTagAnnotation.class));
            }
        }
        return collect;
    }

    private static StanfordCoreNLP propInit(){
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner,parse");
        return new StanfordCoreNLP(props);
    }

}
