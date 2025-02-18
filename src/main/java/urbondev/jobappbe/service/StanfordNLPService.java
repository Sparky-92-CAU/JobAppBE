package urbondev.jobappbe.service;



import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.util.CoreMap;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class StanfordNLPService {

    private final StanfordCoreNLP pipeline;

    public StanfordNLPService() {
        Properties props = new Properties();
        // Initialize the pipeline with tokenization, sentence splitting, part-of-speech tagging, and lemmatization
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma");
        pipeline = new StanfordCoreNLP(props);
    }

    /**
     * Extracts a list of lemmas from the given text.
     *
     * @param text The input text (e.g., extracted resume text)
     * @return A list of lowercased lemmas.
     */
    public List<String> extractLemmas(String text) {
        Annotation document = new Annotation(text);
        pipeline.annotate(document);

        List<String> lemmas = new ArrayList<>();
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        if (sentences != null) {
            for (CoreMap sentence : sentences) {
                for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                    String lemma = token.get(CoreAnnotations.LemmaAnnotation.class);
                    lemmas.add(lemma.toLowerCase());
                }
            }
        }
        return lemmas;
    }
}
