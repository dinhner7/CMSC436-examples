package com.example.xmlcontentappv0

import android.util.Log
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler

class SAXHandler : DefaultHandler() {

    override fun startElement( uri: String?, localName: String?, qName: String?, attributes: Attributes?
    ) {
        super.startElement(uri, localName, qName, attributes)
        Log.w( "MainActivity", "startElement called, qName is = " + qName )
    }

    override fun endElement(uri: String?, localName: String?, qName: String?) {
        super.endElement(uri, localName, qName)
        Log.w( "MainActivity", "endElement called, qName is " + qName )
    }

    override fun characters(ch: CharArray?, start: Int, length: Int) {
        super.characters(ch, start, length)
        if( ch != null ) {
            var text: String = String(ch!!, start, length)
            Log.w("MainActivity", "characters called, text = " + text)
        }
    }
}