package pasa.cbentley.framework.core.j2me.ctx;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.core.src4.interfaces.ITimeCtrl;
import pasa.cbentley.framework.core.j2me.coredata.ctx.CoreDataJ2ME;
import pasa.cbentley.framework.core.j2me.engine.CoordinatorJ2ME;
import pasa.cbentley.framework.core.src4.ctx.CoreFrameworkCtx;
import pasa.cbentley.framework.core.src4.interfaces.IHost;
import pasa.cbentley.framework.core.src4.interfaces.IHostUITools;
import pasa.cbentley.framework.core.src4.interfaces.ILauncherHost;
import pasa.cbentley.framework.coreio.src4.ctx.CoreIOCtx;
import pasa.cbentley.framework.coreio.src4.file.IFileConnection;
import pasa.cbentley.framework.coreui.j2me.ctx.CoreUiJ2MECtx;
import pasa.cbentley.framework.coreui.src4.interfaces.ICanvasHost;

public class CoreJ2MECtx extends CoreFrameworkCtx {

   public static final int CTX_ID = 5001;

   protected final CoreUiJ2MECtx cuc;

   private CoordinatorJ2ME       coordinatorSwing;

   public CoreJ2MECtx(CoreUiJ2MECtx cuc, CoreDataJ2ME dac, CoreIOCtx cioc, ILauncherHost launcher) {
      super(new CoreConfigJ2ME(cuc.getUCtx()), cuc, dac, cioc, launcher);
      this.cuc = cuc;
   }

   public CoreUiJ2MECtx getCoreUiJ2MECtx() {
      return cuc;
   }

   public String getStringIDReal() {
      return "j2me";
   }

   public void setCoordinatorSwing(CoordinatorJ2ME coordinatorSwing) {
      this.coordinatorSwing = coordinatorSwing;
   }

   public CoordinatorJ2ME getCoordinatorSwing() {
      return coordinatorSwing;
   }

   public IFileConnection getFileChooser(ICanvasHost context, ByteObject fcTech) {
      // TODO Auto-generated method stub
      return null;
   }

   public String[] getStackTrace(Throwable e) {
      return null;
   }

   public ITimeCtrl getTimeCtrl() {
      // TODO Auto-generated method stub
      return null;
   }

   public IHost getHost() {
      // TODO Auto-generated method stub
      return null;
   }

   public IHostUITools getHostTools() {
      // TODO Auto-generated method stub
      return null;
   }

   public int getModuleID() {
      return CTX_ID;
   }

}
