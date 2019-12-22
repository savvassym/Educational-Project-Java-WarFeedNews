package com.WarNewsFeed.wnf.backend.nlp;

import com.WarNewsFeed.wnf.helpers.Tuple;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class Nlp {

    private StanfordCoreNLP pip = propInit();
    private List<Tuple<String,String>> collect = new ArrayList<>();


    public List<Tuple<String,String>> analyzer(String input){
        if(input.equals("")){
            Tuple<String,String> emptyTuple = new Tuple<>("","");
            collect.add(emptyTuple);
        }
        else {
            Annotation document = new Annotation(input);
            pip.annotate(document);
            List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
            for (CoreMap sentence : sentences) {
                for (CoreLabel tokens : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                    Tuple<String, String> tuple = new Tuple<>(tokens.getString(CoreAnnotations.TextAnnotation.class),
                            tokens.get(CoreAnnotations.NamedEntityTagAnnotation.class));
                    collect.add(tuple);
                }
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
