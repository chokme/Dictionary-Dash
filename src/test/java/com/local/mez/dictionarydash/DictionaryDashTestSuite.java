package com.local.mez.dictionarydash;

import com.local.mez.dictionarydash.common.algorithms.DijkstrasAlgoTest;
import com.local.mez.dictionarydash.common.graphs.GraphGeneratorTest;
import com.local.mez.dictionarydash.common.graphs.SingleCharDiffGraphProcessorTest;
import com.local.mez.dictionarydash.utils.CsvFileReaderTest;
import com.local.mez.dictionarydash.utils.DictionaryGeneratorTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ShortestWordTransformationConfigurationTest.class,
        CsvFileReaderTest.class,
        DictionaryGeneratorTest.class,
        GraphGeneratorTest.class,
        SingleCharDiffGraphProcessorTest.class,
        DijkstrasAlgoTest.class
})
public class DictionaryDashTestSuite {
}

