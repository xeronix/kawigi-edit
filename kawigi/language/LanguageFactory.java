package kawigi.language;

/**
 *	Creates EditorLanguage objects based on a language name or filetype.
 *
 *	This class was really simple at some point in time and returned TopCoder
 *	languages.  Then I wanted to associate more data with languages, so I
 *	subclassed all the TopCoder languages and made this class return those.
 *	Then I wanted the template code generated by those subclassed languages
 *	to be customizable by allowing the user to indirectly write their own
 *	subclass of my subclassed languages, and this class became this gigantic
 *	monstrosity of dynamic class-loading.
 *
 *	Then I wanted to make it so that KawigiEdit could be run standalone without
 *	having the TopCoder classes in the classpath, and this class was made simple
 *	again, just returning subclasses of my EditorLanguage class. Actually, the
 *	only remaining use of this class is to figure out which language to use to
 *	render code opened in the Local Code tab, which probably means it's not
 *	worth this long comment. It isn't even used to map TC languages to
 *	KawigiEdit EditorLanguages, because that would require linking to TopCoder
 *	classes, and this class needs to be usable in standalone mode.
 **/
public final class LanguageFactory
{
	/**
	 * Private constructor for utility class
	 */
	private LanguageFactory() {}

	/**
	 * Returns the EditorLanguage with the given name, or that supports the
	 * file extension given by name.
	 *
	 * @param name      Name of the language or extension of the file
	 * @return          Language for this name
	 **/
	public static EditorLanguage getLanguage(String name)
	{
		EditorLanguage res = null;
		if ("java".equalsIgnoreCase(name))
			res = JavaLang.getInstance();
		// a small subset of names and file extensions that should use the C++
		// language objects - less than there are, more than I really need.
		else if ("cpp".equalsIgnoreCase(name) || "c++".equalsIgnoreCase(name) || "h".equalsIgnoreCase(name)
		         || "c".equalsIgnoreCase(name) || "cxx".equalsIgnoreCase(name))
			res = CPPLang.getInstance();
		else if ("csharp".equalsIgnoreCase(name) || "c#".equalsIgnoreCase(name) || "cs".equalsIgnoreCase(name))
			res = CSharpLang.getInstance();
		else if ("vb".equalsIgnoreCase(name) || "bas".equalsIgnoreCase(name) || "basic".equalsIgnoreCase(name))
			res = VBLang.getInstance();
        else if ("py".equalsIgnoreCase(name) || "python".equalsIgnoreCase(name))
            res = PythonLang.getInstance();
		return res;
	}
}
