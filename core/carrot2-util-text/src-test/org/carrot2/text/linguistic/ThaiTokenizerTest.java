/*
 * Carrot2 project.
 *
 * Copyright (C) 2002-2011, Dawid Weiss, Stanisław Osiński.
 * All rights reserved.
 *
 * Refer to the full license file "carrot2.LICENSE"
 * in the root folder of the repository checkout or at:
 * http://www.carrot2.org/carrot2.LICENSE
 */

package org.carrot2.text.linguistic;

import static org.junit.Assume.assumeTrue;

import java.io.IOException;

import org.carrot2.core.LanguageCode;
import org.carrot2.text.analysis.ITokenizer;
import org.carrot2.text.linguistic.lucene.ThaiTokenizerAdapter;
import org.junit.Test;

/**
 * Test cases for {@link ThaiTokenizerAdapter}. Test strings taken from Lucene's
 * TestThaiAnalyzer.
 */
public class ThaiTokenizerTest extends TokenizerTestBase
{
    @Override
    protected ITokenizer createTokenStream() throws IOException
    {
        return new DefaultTokenizerFactory().getTokenizer(LanguageCode.THAI);
    }

    @Test
    public void testThaiTermTokens()
    {
        assumeTrue(DefaultTokenizerFactory.THAI_TOKENIZATION_SUPPORTED);
        assertEqualTokens(
            "การที่ได้ต้องแสดงว่างานดี",
            tokens(ITokenizer.TT_TERM, "การ", "ที่", "ได้", "ต้อง", "แสดง", "ว่า", "งาน",
                "ดี"));
    }

    @Test
    public void testThaiEnglishTermTokens()
    {
        assumeTrue(DefaultTokenizerFactory.THAI_TOKENIZATION_SUPPORTED);
        assertEqualTokens("ประโยคว่า The quick brown",
            tokens(ITokenizer.TT_TERM, "ประโยค", "ว่า", "The", "quick", "brown"));
    }

    @Test
    public void testNumericTokens()
    {
        assumeTrue(DefaultTokenizerFactory.THAI_TOKENIZATION_SUPPORTED);
        assertEqualTokens("๑๒๓", tokens(ITokenizer.TT_NUMERIC, "๑๒๓"));
    }
}
