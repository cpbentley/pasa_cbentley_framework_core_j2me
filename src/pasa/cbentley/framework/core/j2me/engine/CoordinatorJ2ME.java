package pasa.cbentley.framework.core.j2me.engine;

import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;

import pasa.cbentley.framework.core.j2me.ctx.CoreJ2MECtx;
import pasa.cbentley.framework.core.src4.engine.CoordinatorAbstract;
import pasa.cbentley.framework.coreui.j2me.engine.CanvasJ2ME;

public class CoordinatorJ2ME extends CoordinatorAbstract {

   protected final CoreJ2MECtx j2meCtx;

   private LaunchJ2ME          launcherJ2ME;

   public CoordinatorJ2ME(CoreJ2MECtx j2meCtx, LaunchJ2ME launcherJ2ME) {
      super(j2meCtx, launcherJ2ME);
      this.j2meCtx = j2meCtx;
      this.launcherJ2ME = launcherJ2ME;
   }

   public Displayable getJ2MEDisplayable() {
      CanvasJ2ME cb = (CanvasJ2ME) j2meCtx.getCUC().getRootCanvas();
      //might be null if appli does not create a canvas
      if (cb != null) {
         return cb.getDisplayable();
      }
      return null;
   }

   public MIDlet getMIDlet() {
      return launcherJ2ME;
   }

   public LaunchJ2ME getLauncherJ2ME() {
      return launcherJ2ME;
   }

   protected void startUIThread() {
      initUIThreadInside();
   }

   public boolean loadLastState() {
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
