package pasa.cbentley.framework.core.framework.j2me.engine;

import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;

import pasa.cbentley.framework.core.framework.j2me.ctx.CoreFrameworkJ2meCtx;
import pasa.cbentley.framework.core.framework.src4.engine.CoordinatorAbstract;
import pasa.cbentley.framework.core.ui.j2me.engine.CanvasJ2me;

public class CoordinatorJ2me extends CoordinatorAbstract {

   protected final CoreFrameworkJ2meCtx j2meCtx;

   private LauncherJ2meMIDlet          launcherJ2ME;

   public CoordinatorJ2me(CoreFrameworkJ2meCtx j2meCtx, LauncherJ2meMIDlet launcherJ2ME) {
      super(j2meCtx, launcherJ2ME);
      this.j2meCtx = j2meCtx;
      this.launcherJ2ME = launcherJ2ME;
   }

   public Displayable getJ2MEDisplayable() {
      CanvasJ2me cb = (CanvasJ2me) j2meCtx.getCUC().getCanvasRootHost();
      //might be null if appli does not create a canvas
      if (cb != null) {
         return cb.getDisplayable();
      }
      return null;
   }

   public MIDlet getMIDlet() {
      return launcherJ2ME;
   }

   public LauncherJ2meMIDlet getLauncherJ2ME() {
      return launcherJ2ME;
   }

   protected void startUIThread() {
      initUIThreadInside();
   }

   public boolean subLoadLastState() {
      // TODO Auto-generated method stub
      return false;
   }

   protected void subExit() {

   }

   protected void subResume() {
   }

   protected void subPause() {
   }

}
