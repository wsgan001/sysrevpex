/*
 * ***** BEGIN LICENSE BLOCK *****
 * 
 * Copyright (c) 2005-2007 Universidade de Sao Paulo, Sao Carlos/SP, Brazil.
 * All Rights Reserved.
 * 
 * This file is part of Projection Explorer (PEx).
 * 
 * How to cite this work:
 * 
 * @inproceedings{paulovich2007pex,
 * author = {Fernando V. Paulovich and Maria Cristina F. Oliveira and Rosane
 * Minghim},
 * title = {The Projection Explorer: A Flexible Tool for Projection-based
 * Multidimensional Visualization},
 * booktitle = {SIBGRAPI '07: Proceedings of the XX Brazilian Symposium on
 * Computer Graphics and Image Processing (SIBGRAPI 2007)},
 * year = {2007},
 * isbn = {0-7695-2996-8},
 * pages = {27--34},
 * doi = {http://dx.doi.org/10.1109/SIBGRAPI.2007.39},
 * publisher = {IEEE Computer Society},
 * address = {Washington, DC, USA},
 * }
 * 
 * PEx is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 * 
 * PEx is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 * 
 * This code was developed by members of Computer Graphics and Image
 * Processing Group (http://www.lcad.icmc.usp.br) at Instituto de Ciencias
 * Matematicas e de Computacao - ICMC - (http://www.icmc.usp.br) of
 * Universidade de Sao Paulo, Sao Carlos/SP, Brazil. The initial developer
 * of the original code is Fernando Vieira Paulovich <fpaulovich@gmail.com>.
 * 
 * Contributor(s): Rosane Minghim <rminghim@icmc.usp.br>
 * 
 * You should have received a copy of the GNU General Public License along
 * with PEx. If not, see <http://www.gnu.org/licenses/>.
 * 
 * ***** END LICENSE BLOCK *****
 */

package visualizer.corpus.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.ironiacorp.datastructure.StringCircularBuffer;
import com.ironiacorp.string.StringUtil;

import visualizer.corpus.BaseCorpus;
import visualizer.corpus.Corpus;
import visualizer.corpus.Encoding;
import visualizer.corpus.database.DataBaseCorpus;
import visualizer.textprocessing.Ngram;
import visualizer.textprocessing.RegExpTermExtractor;
import visualizer.textprocessing.TermExtractor;

/**
 * 
 * @author Fernando Vieira Paulovich
 */
public class InvertedZipCorpus
{
	private static final String CONFIGURATION_FILENAME = "inverted.properties";
	
	private static final String NGRAMS_FILENAME = "corpusNgrams.txt";

	private static final String INVERTED_DIRECTORY = "inv" + File.separator;
	
	public static final String FILENAME_EXTENSION = ".inv";
	
	private String invFilename;

	private ZipFile zip;

	private ZipCorpus corpus;

	private int nrGrams;

	private Encoding encoding;

	/**
	 * Creates a new instance of InvertedFile.
	 * 
	 * @param corpus
	 * @param nrGrams
	 * @param invFilename
	 */
	public InvertedZipCorpus(ZipCorpus corpus, int nrGrams, String invFilename)
	{
		this.invFilename = invFilename;
		this.corpus = corpus;
		this.nrGrams = nrGrams;
		this.encoding = BaseCorpus.getEncoding();

		removeFile();
		processCorpus();
		dispose();
	}

	public void removeFile()
	{
		// if the inverted file exists, remove it.
		File f = new File(invFilename);
		if (f.exists()) {
			f.delete();
		}
	}

	public List<Ngram> getNgrams(String filename)
	{
		List<Ngram> ngrams = null;
		BufferedInputStream bis = null;
		ObjectInputStream ois = null;

		try {
			if (zip == null) {
				zip = new ZipFile(invFilename);
			}

			ZipEntry entry = zip.getEntry(INVERTED_DIRECTORY + filename);
			if (entry != null) {
				bis = new BufferedInputStream(zip.getInputStream(entry));
				ois = new ObjectInputStream(bis);
				ngrams = (List<Ngram>) ois.readObject();
				ois.close();
			}
		} catch (InvalidClassException ex) {
			Logger.getLogger(InvertedZipCorpus.class.getName()).log(Level.SEVERE, null, ex);
			dispose();
			removeFile();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(InvertedZipCorpus.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(DataBaseCorpus.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				if (ois != null) {
					ois.close();
				}
			} catch (IOException e) {
			}
		}

		return ngrams;
	}

	public List<Ngram> getCorpusNgrams()
	{
		List<Ngram> ngrams = null;
		BufferedInputStream bis = null;
		ObjectInputStream ois = null;

		try {
			if (zip == null) {
				zip = new ZipFile(invFilename);
			}

			ZipEntry entry = zip.getEntry(NGRAMS_FILENAME);
			if (entry != null) {
				bis = new BufferedInputStream(zip.getInputStream(entry));
				ois = new ObjectInputStream(bis);
				ngrams = (List<Ngram>) ois.readObject();
			}

		} catch (InvalidClassException ex) {
			Logger.getLogger(InvertedZipCorpus.class.getName()).log(Level.SEVERE, null, ex);
			this.dispose();
			this.removeFile();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(InvertedZipCorpus.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(DataBaseCorpus.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				if (ois != null) {
					ois.close();
				}
			} catch (IOException e) {
			}
		}

		return ngrams;
	}

	public void dispose()
	{
		if (zip != null) {
			try {
				zip.close();
			} catch (IOException ex) {
				Logger.getLogger(InvertedZipCorpus.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				zip = null;
			}
		}
	}

	public String getInvFilename()
	{
		return invFilename;
	}

	private void processCorpus()
	{
		Map<String, Integer> corpusNgrams = new HashMap<String, Integer>();
		ZipOutputStream zout = null;

		try {
			FileOutputStream dest = new FileOutputStream(invFilename);
			zout = new ZipOutputStream(new BufferedOutputStream(dest));
			zout.setMethod(ZipOutputStream.DEFLATED);
			zout.setLevel(Deflater.BEST_SPEED);

			// Add the number of grams and encoding to the inverted corpus properties file
			ZipEntry entry = new ZipEntry(CONFIGURATION_FILENAME);
			zout.putNextEntry(entry);
			String prop = "number.grams=" + nrGrams + "\n";
			zout.write(prop.getBytes(), 0, prop.length());
			prop = "char.encoding=" + encoding.toString() + "\n";
			zout.write(prop.getBytes(), 0, prop.length());

			// Add the ngrams of each document of the corpus. Each document is named by a single number.
			for (int i = 0; i < corpus.getIds().size(); i++) {
				List<Ngram> ngrams = getNgramsFromFile(corpus.getIds().get(i));
				Iterator<Ngram> iNgram;
				addFile(zout, ngrams, INVERTED_DIRECTORY + corpus.getIds().get(i));

				iNgram = ngrams.iterator();
				while (iNgram.hasNext()) {
					Ngram n = iNgram.next();
					if (corpusNgrams.containsKey(n.getNgram())) {
						corpusNgrams.put(n.getNgram(), corpusNgrams.get(n.getNgram()) + n.getFrequency());
					} else {
						corpusNgrams.put(n.getNgram(), n.getFrequency());
					}
				}
			}

			List<Ngram> ngrams = new ArrayList<Ngram>();
			for (String key : corpusNgrams.keySet()) {
				ngrams.add(new Ngram(key, corpusNgrams.get(key)));
			}

			Collections.sort(ngrams, new Ngram.ForgivingComparator());
			addFile(zout, ngrams, NGRAMS_FILENAME);

		} catch (FileNotFoundException ex) {
			Logger.getLogger(InvertedZipCorpus.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(InvertedZipCorpus.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				if (zout != null) {
					zout.flush();
					zout.close();
				}
			} catch (IOException ex) {
				Logger.getLogger(InvertedZipCorpus.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	private void addFile(ZipOutputStream zout, List<Ngram> ngrams, String filename) throws IOException
	{
		ZipEntry entry = new ZipEntry(filename);
		ObjectOutputStream oos;
		
		zout.putNextEntry(entry);
		oos = new ObjectOutputStream(zout);
		Collections.sort(ngrams, new Ngram.ForgivingComparator());
		oos.writeObject(ngrams);
		oos.flush();
	}

	/**
	 * Method that creates the ngrams.
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	private List<Ngram> getNgramsFromFile(String filename) throws IOException
	{
		String filecontent = corpus.getFullContent(filename);
		List<Ngram> ngrams = new ArrayList<Ngram>();

		if (filecontent == null || StringUtil.isEmpty(filecontent)) {
			return ngrams;
		}

		Multiset<String> bag = HashMultiset.create();
		TermExtractor<String> termExtractor = new RegExpTermExtractor(filecontent); 
		
		StringCircularBuffer[] buffers = new StringCircularBuffer[nrGrams - 1];
		for (int i = 2; i <= nrGrams; i++) {
			buffers[i - 2] = new StringCircularBuffer(i);
			buffers[i - 2].setSeparator(Corpus.NGRAM_SEPARATOR);
		}
		
		String term;
		while ((term = termExtractor.next()) != null) {
			if (! StringUtil.isEmpty(term)) {
				bag.add(term);
				for (StringCircularBuffer buffer : buffers) { 
					String ngram = buffer.add(term);
					if (ngram != null) {
						bag.add(ngram);
					}
				}
			}
		}
		for (StringCircularBuffer buffer : buffers) { 
			String leftover = buffer.reset();
			if (leftover != null) {
				bag.add(leftover);
			}
		}
		
		Iterator<String> i = bag.iterator();
		while (i.hasNext()) {
			String ngramText = i.next();
			Ngram ngram = new Ngram(ngramText);
			ngram.setFrequency(bag.count(ngramText));
			ngrams.add(ngram);
		}

		return ngrams;
	}

}
