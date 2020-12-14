package pasa.cbentley.framework.core.j2me.ctx;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.framework.coreio.src4.IConnector;
import pasa.cbentley.framework.coreio.src4.ctx.CoreIOCtx;
import pasa.cbentley.framework.coreio.src4.ctx.IConfigCoreIO;

public class CoreIOJ2Me extends CoreIOCtx {

   public CoreIOJ2Me(UCtx uc, IConfigCoreIO config) {
      super(config,uc);
   }

   public IConnector getConnector() {
      // TODO Auto-generated method stub
      return null;
   }

}
