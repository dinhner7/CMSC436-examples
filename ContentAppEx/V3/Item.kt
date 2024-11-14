package com.example.xmlcontentappv3

class Item {
    private var title : String = ""
    private var link : String = ""

    fun getTitle( ) : String {
        return title
    }

    fun getLink( ) : String {
        return link
    }

    fun setLink( link : String ) {
        this.link = link
    }

    fun setTitle( title : String ) {
        this.title = title
    }

    override fun toString( ) : String {
        return "title: " + title + "; link: " + link
    }
}