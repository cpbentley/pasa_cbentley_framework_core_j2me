package pasa.cbentley.framework.core.framework.j2me.engine;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.framework.core.framework.j2me.ctx.CoreFrameworkJ2meCtx;
import pasa.cbentley.framework.core.framework.src4.ctx.ObjectCFC;
import pasa.cbentley.framework.core.framework.src4.interfaces.IHostCoreTools;
import pasa.cbentley.framework.core.io.src4.file.IFileConnection;
import pasa.cbentley.framework.core.ui.src4.interfaces.ICanvasHost;

public class HostToolsJ2me extends ObjectCFC implements IHostCoreTools {

   public HostToolsJ2me(CoreFrameworkJ2meCtx cfc) {
      super(cfc);
   }

   public void flashLightToggle() {
      
   }

   public IFileConnection getFileChooser(ICanvasHost context, ByteObject fcTech) {
      // TODO Auto-generated method stub
      return null;
   }

}
