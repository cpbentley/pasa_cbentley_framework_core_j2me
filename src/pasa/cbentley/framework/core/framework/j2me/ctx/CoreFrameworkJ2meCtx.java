package pasa.cbentley.framework.core.framework.j2me.ctx;

import pasa.cbentley.core.src4.interfaces.IHost;
import pasa.cbentley.framework.core.data.j2me.ctx.CoreDataJ2meCtx;
import pasa.cbentley.framework.core.draw.j2me.ctx.CoreDrawJ2meCtx;
import pasa.cbentley.framework.core.draw.j2me.engine.HostJ2me;
import pasa.cbentley.framework.core.framework.j2me.engine.CoordinatorJ2me;
import pasa.cbentley.framework.core.framework.src4.ctx.CoreFrameworkCtx;
import pasa.cbentley.framework.core.framework.src4.ctx.IConfigCoreFramework;
import pasa.cbentley.framework.core.framework.src4.interfaces.IHostCoreTools;
import pasa.cbentley.framework.core.framework.src4.interfaces.ILauncherHost;
import pasa.cbentley.framework.core.io.j2me.ctx.CoreIOJ2meCtx;
import pasa.cbentley.framework.core.ui.j2me.ctx.CoreUiJ2meCtx;

public class CoreFrameworkJ2meCtx extends CoreFrameworkCtx {

   public static final int       CTX_ID = 5001;

   private CoordinatorJ2me       coordinatorJ2me;

   protected final CoreUiJ2meCtx cuc;

   public CoreFrameworkJ2meCtx(CoreUiJ2meCtx cuc, CoreDataJ2meCtx dac, CoreIOJ2meCtx cioc, ILauncherHost launcher) {
      this(new CoreConfigJ2me(cuc.getUC()), cuc, dac, cioc, launcher);
   }

   public CoreFrameworkJ2meCtx(IConfigCoreFramework config, CoreUiJ2meCtx cuc, CoreDataJ2meCtx dac, CoreIOJ2meCtx cioc, ILauncherHost launcher) {
      super(config, cuc, dac, cioc, launcher);
      this.cuc = cuc;
   }

   public IHost createHost() {
      CoreDrawJ2meCtx cdc = cuc.getCoreDrawJ2MECtx();
      return new HostJ2me(cdc);
   }

   public CoordinatorJ2me getCoordinatorJ2me() {
      return coordinatorJ2me;
   }

   public CoreUiJ2meCtx getCoreUiJ2MECtx() {
      return cuc;
   }

   public IHostCoreTools getHostTools() {
      // TODO Auto-generated method stub
      return null;
   }

   public int getModuleID() {
      return CTX_ID;
   }

   public String[] getStackTrace(Throwable e) {
      return null;
   }

   public String getStringIDReal() {
      return "j2me";
   }

   public void setCoordinatorJ2me(CoordinatorJ2me coordinatorSwing) {
      this.coordinatorJ2me = coordinatorSwing;
   }

}
