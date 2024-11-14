package com.example.xmlcontentappv4

import android.util.Log
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler

class SAXHandler : DefaultHandler() {
    // V1
    private var items : ArrayList<Item> = ArrayList<Item>( )
    private var validText : Boolean = false
    private var currentItem : Item? = null
    private var element : String = ""

    override fun startElement( uri: String?, localName: String?, qName: String?, attributes: Attributes?
    ) {
        super.startElement(uri, localName, qName, attributes)
        // Log.w( "MainActivity", "startElement called, qName is = " + qName )

        // V1
        validText = true
        if( qName != null ) {
            element = qName
            if( element.equals( "item" ) ) {
                currentItem = Item( )
            }
        }
    }

    override fun endElement(uri: String?, localName: String?, qName: String?) {
        super.endElement(uri, localName, qName)
        // Log.w( "MainActivity", "endElement called, qName is " + qName )

        // V1
        validText = false
        if( currentItem != null && qName != null && qName.equals( "item" ) ) {
            items.add( currentItem!! )
        }
    }

    override fun characters(ch: CharArray?, start: Int, length: Int) {
        super.characters(ch, start, length)
        if( ch != null ) {
            var text: String = String(ch!!, start, length)
            // Log.w("MainActivity", "characters called, text = " + text)

            // V1
            if( currentItem != null && element.equals( "title" ) && validText ) {
                currentItem!!.setTitle( text )
            } else if( currentItem != null && element.equals( "link" ) && validText ) {
                currentItem!!.setLink( text )
            }
        }
    }

     fun getItems( ) : ArrayList<Item> {
         return items
     }
}