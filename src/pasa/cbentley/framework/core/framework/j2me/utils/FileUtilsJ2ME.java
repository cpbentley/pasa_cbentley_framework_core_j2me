package pasa.cbentley.framework.core.framework.j2me.utils;

/**
 * For SE Phones:
 * To find out what versions of the JSR-75 APIs are implemented in the phone:
System.getProperty("microedition.io.file.FileConnection.version")
System.getProperty("microedition.pim.version")
The following file connection API properties are URLs of default storage directories in the phone, retrieved
with the System.getProperty() call:
fileconn.dir.photos
fileconn.dir.videos
fileconn.dir.graphics
fileconn.dir.tones
fileconn.dir.music
fileconn.dir.recordings
fileconn.dir.private
Localised names of directories corresponding to the default URLs above are found in the following
properties:
fileconn.dir.photos.name
fileconn.dir.videos.name
fileconn.dir.graphics.name
fileconn.dir.tones.name
fileconn.dir.music.name
fileconn.dir.recordings.name
fileconn.dir.private.name
The following call returns localised names to the roots returned by the
FileSystemRegistry.listRoots() method. The returned names are listed in the same order as
returned by this method and are separated by semicolon (;):
System.getProperty("fileconn.dir.roots.names")
Note: Property retrieval behaviour differs slightly between some early JP-6 phone models and other
phones, due to changes in the fileconn property syntax. Null may be returned when using the above
syntax with some early JP-6 phones. The following code could be used to provide a generic means to
address this behaviour difference:
public String getProperty(String param)
{
int index = param.indexOf(".");
String extension = param.substring(index,param.length());
String value = System.getProperty("fileconn" + extension);
return value != null ? value : System.getProperty("filconn" + extension);
 * @author Charles-Philip Bentley
 *
 */
public class FileUtilsJ2ME {



}
