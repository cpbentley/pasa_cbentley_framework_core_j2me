package pasa.cbentley.framework.core.j2me.engine;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import pasa.cbentley.byteobjects.src4.ctx.BOCtx;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.logging.ILogConfigurator;
import pasa.cbentley.core.src4.logging.LogJournal;
import pasa.cbentley.framework.core.j2me.coredata.ctx.CoreDataJ2ME;
import pasa.cbentley.framework.core.j2me.coredata.engine.J2MERMS;
import pasa.cbentley.framework.core.j2me.ctx.CoreIOJ2Me;
import pasa.cbentley.framework.core.j2me.ctx.CoreJ2MECtx;
import pasa.cbentley.framework.core.src4.app.IAppli;
import pasa.cbentley.framework.core.src4.app.IConfigApp;
import pasa.cbentley.framework.core.src4.ctx.CoreFrameworkCtx;
import pasa.cbentley.framework.core.src4.engine.CoordinatorAbstract;
import pasa.cbentley.framework.core.src4.engine.LaunchValues;
import pasa.cbentley.framework.core.src4.interfaces.ILauncherAppli;
import pasa.cbentley.framework.core.src4.interfaces.ILauncherHost;
import pasa.cbentley.framework.coredata.src4.ctx.CoreDataCtx;
import pasa.cbentley.framework.coredata.src4.ctx.IConfigCoreData;
import pasa.cbentley.framework.coredata.src4.db.IByteRecordStoreFactory;
import pasa.cbentley.framework.coredata.src4.engine.ConfigManager;
import pasa.cbentley.framework.coredraw.j2me.ctx.CoreDrawJ2MECtx;
import pasa.cbentley.framework.coredraw.src4.ctx.IConfigCoreDraw;
import pasa.cbentley.framework.coredraw.src4.interfaces.ITechDrawer;
import pasa.cbentley.framework.coreio.src4.ctx.IConfigCoreIO;
import pasa.cbentley.framework.coreui.j2me.ctx.CoreUiJ2MECtx;
import pasa.cbentley.framework.coreui.src4.ctx.IConfigCoreUI;

/**
 * Hosts the {@link MIDlet} reference.
 * <br>
 * Compared to Swing or SWT, the Launcher is a MIDLET.
 * <br>
 * <br>
 * 
 * @author Charles Bentley
 *
 */
public abstract class LaunchJ2ME extends MIDlet implements ILauncherHost, IConfigCoreIO, IConfigCoreData {

   protected CoordinatorJ2ME coordinator;

   protected CoreJ2MECtx     cac;

   private ConfigManager     cm;

   protected final BOCtx     boc;

   protected final UCtx      uc;

   public String getConfigIODirectory() {
      return "";
   }

   /**
    * Create the {@link UCtx}, {@link BOCtx}
    * @param la
    */
   public LaunchJ2ME() {
      uc = new UCtx();
      boc = new BOCtx(uc);
      //read cfg state from disk. we need App Manifest for store name
      CoreDataJ2ME dac = new CoreDataJ2ME(this, boc);
      IConfigApp appConfig = createConfigApp(uc);
      String storeName = appConfig.getAppName();
      cm = new ConfigManager(dac, storeName);
      cm.settingsRead();

      //load the config. depending how their values, it will override saved data
      IConfigCoreDraw configDraw = getConfigDraw();
      //creates a config from factory and saved settings
      //give a reference to the midlet since we need it for some actions
      CoreDrawJ2MECtx cdc = new CoreDrawJ2MECtx(uc, boc, this, configDraw);
      IConfigCoreUI configUI = getConfigUI();
      CoreUiJ2MECtx cuc = new CoreUiJ2MECtx(cdc, configUI);
      CoreIOJ2Me cioc = new CoreIOJ2Me(uc, this);
      CoreJ2MECtx cac = new CoreJ2MECtx(cuc, dac, cioc, this);
      this.cac = cac;
      coordinator = new CoordinatorJ2ME(cac, this);
   }

   /**
    * Creates the {@link ILauncherAppli} for the {@link IAppli}.
    * The launcher will have the host configuration and create its specific
    * {@link IConfigApp}
    * @return
    */
   protected abstract ILauncherAppli createLauncher(UCtx uc);

   public CoordinatorAbstract getCoordinator() {
      return coordinator;
   }

   public void appExit() {
      cm.settingsWrite();
   }

   public void appPause() {
      cm.settingsWrite();
   }

   public abstract IConfigCoreDraw getConfigDraw();

   public abstract IConfigCoreUI getConfigUI();

   /**
    * Creates a hardcoded config. It will be used to read from disk for a saved config
    * @param uc
    * @return
    */
   public abstract IConfigApp createConfigApp(UCtx uc);

   protected void destroyApp(boolean uncond) throws MIDletStateChangeException {
      coordinator.frameworkExit();
   }

   protected void pauseApp() {
      coordinator.frameworkPause();
   }

   public CoreFrameworkCtx getCFC() {
      return cac;
   }

   public ILogConfigurator toStringGetLoggingConfig() {
      return new LoggingConfigJ2ME(cac);
   }

   public void startAppli(ILauncherAppli launcherAppli) {
      coordinator.frameworkStart(launcherAppli);
   }

   /**
    * Override this when needed
    */
   public void setLaunchValues(LaunchValues lv) {

   }

   public void setOSSpecifics(CoreFrameworkCtx hoc) {
   }

   /**
    * Signals the MIDlet that it has entered the Active state.
    * <br>
    * {@link MIDlet} method called by the J2ME framework
    */
   protected void startApp() throws MIDletStateChangeException {
      ILauncherAppli la = createLauncher(uc);
      startAppli(la );
   }

   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "LauncherJ2ME");
      toStringPrivate(dc);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "LauncherJ2ME");
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return cac.getUCtx();
   }

   //#enddebug

}
