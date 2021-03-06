package visualizer.representation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import visualizer.matrix.SparseMatrix;
import visualizer.matrix.SparseVector;
import visualizer.projection.ProjectionData;
import visualizer.textprocessing.Ngram;

public class VectorSpaceRepresentation extends AbstractRepresentation {

    public VectorSpaceRepresentation(Corpus corpus) {
        super(corpus);
    }

    @Override
    public SparseMatrix getMatrix(long[] ids, ProjectionData pdata) throws IOException {
        SparseMatrix matrix = new SparseMatrix();
        int ngramssize = this.ngrams.size();
        double[] row;
        HashMap<String, Integer> docNgrams;
        ArrayList<Reference> references = null;
        int n_citations = 0;
        if (include_references) {
            references = corpus.getCorpusReferences(pdata.getReferencesLowerCut(), pdata.getReferencesUpperCut());
            n_citations = references.size();
        }

        for (int i = 0; i < ids.length; i++) {
            if (include_references) {
                row = new double[ngramssize + n_citations];
            } else {
                row = new double[ngramssize];
            }

            Arrays.fill(row, 0.0d);
            //get the ngrams of the file
            docNgrams = getNgrams(ids[i]);
            Ngram n;
            for (int j = 0; j < ngramssize; j++) {
                n = this.ngrams.get(j);
                if (docNgrams.containsKey(n.ngram)) {
                    row[j] = docNgrams.get(n.ngram);
                }
            }

            if ((include_references) && references != null && !references.isEmpty()) {
                int index = 0;
                for (Reference ref : references) {
                    if (corpus.doesThisDocumentCitesThisReference(ids[i], ref.indexDatabase)) {
                        row[ngramssize + index] = 1.0f;
                    }
                    index++;
                }
            }

            SparseVector sv = new SparseVector(row, ids[i], 0);
            sv.setTitle(this.corpus.getTitle(ids[i]));

            matrix.addRow(sv);
        }
        ArrayList<String> attr = new ArrayList<>();
        for (Ngram n : this.ngrams) {
            attr.add(n.ngram);
        }
        if (include_references) {
            for (Reference ref : references) {
                attr.add("ref-" + ref.indexDatabase);
            }
        }
        matrix.setAttributes(attr);
        return matrix;
    }
}
