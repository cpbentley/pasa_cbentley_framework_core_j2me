/*
 * (c) 2018-2024 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.framework.core.framework.j2me.engine;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import pasa.cbentley.byteobjects.src4.ctx.BOCtx;
import pasa.cbentley.byteobjects.src4.ctx.IConfigBOC;
import pasa.cbentley.core.src4.ctx.IConfigU;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.logging.ILogConfigurator;
import pasa.cbentley.framework.core.data.j2me.ctx.CoreDataJ2meCtx;
import pasa.cbentley.framework.core.data.src4.ctx.IConfigCoreData;
import pasa.cbentley.framework.core.draw.j2me.ctx.CoreDrawJ2meCtx;
import pasa.cbentley.framework.core.framework.j2me.ctx.CoreFrameworkJ2meCtx;
import pasa.cbentley.framework.core.framework.src4.app.IAppli;
import pasa.cbentley.framework.core.framework.src4.app.IConfigApp;
import pasa.cbentley.framework.core.framework.src4.ctx.CoreFrameworkCtx;
import pasa.cbentley.framework.core.framework.src4.ctx.IConfigCoreFramework;
import pasa.cbentley.framework.core.framework.src4.engine.CoordinatorAbstract;
import pasa.cbentley.framework.core.framework.src4.interfaces.ICreatorAppli;
import pasa.cbentley.framework.core.framework.src4.interfaces.ILauncherHost;
import pasa.cbentley.framework.core.io.j2me.ctx.CoreIOJ2meCtx;
import pasa.cbentley.framework.core.io.src4.ctx.IConfigCoreIO;
import pasa.cbentley.framework.core.ui.j2me.ctx.CoreUiJ2meCtx;
import pasa.cbentley.framework.core.ui.src4.ctx.IConfigCoreUi;
import pasa.cbentley.framework.coredraw.src4.ctx.IConfigCoreDraw;

/**
 * Hosts the {@link MIDlet} reference.
 * Compared to Swing or SWT, the Launcher is a MIDLET.
 * 
 * @author Charles Bentley
 *
 */
public abstract class LauncherJ2meMIDlet extends MIDlet implements ILauncherHost {

   protected final BOCtx          boc;

   protected CoreFrameworkJ2meCtx cac;

   protected final ConfigJ2me     config;

   protected CoordinatorJ2me      coordinator;

   protected final UCtx           uc;

   /**
    * Create the {@link UCtx}, {@link BOCtx}
    * @param la
    */
   public LauncherJ2meMIDlet() {

      config = createConfigJ2me();
      //no logger yet at this stage
      IConfigU configu = getConfigU(); //configU fetches the ILogConfigurator

      //#mdebug
      //set the logconfigurator if none
      ILogConfigurator logConfigurator = this.toStringGetLoggingConfig();
      configu.toStringSetLogConfigurator(logConfigurator);
      //#enddebug

      uc = new UCtx(configu, "LaunchJ2me"); //constructor deals smoothly with a null

      IConfigBOC configBO = getConfigBOC(); //configU fetches the ILogConfigurator

      boc = new BOCtx(configBO, uc);

      //once uc is created. create configj2me
      //read cfg state from disk. we need App Manifest for store name
      IConfigCoreData configData = getConfigData();
      CoreDataJ2meCtx dac = new CoreDataJ2meCtx(configData, boc);

      //load the config. depending how their values, it will override saved data
      //creates a config from factory and saved settings
      //give a reference to the midlet since we need it for some actions
      IConfigCoreDraw configDraw = getConfigDraw();
      CoreDrawJ2meCtx cdc = new CoreDrawJ2meCtx(uc, boc, this, configDraw);

      IConfigCoreUi configUI = getConfigUi();
      CoreUiJ2meCtx cuc = new CoreUiJ2meCtx(cdc, configUI);

      IConfigCoreIO configIO = getConfigIO();
      CoreIOJ2meCtx cioc = new CoreIOJ2meCtx(uc, configIO);

      IConfigCoreFramework configFramework = getConfigFramework();
      CoreFrameworkJ2meCtx cfc = new CoreFrameworkJ2meCtx(configFramework, cuc, dac, cioc, this);

      this.cac = cfc;
      coordinator = new CoordinatorJ2me(cfc, this);
   }

   public void appExit() {
   }

   public void appPause() {
   }

   public abstract ConfigJ2me createConfigJ2me();

   /**
    * Creates the {@link ICreatorAppli} for the {@link IAppli}.
    * The launcher will have the host configuration and create its specific
    * {@link IConfigApp}
    * @return
    */
   protected abstract ICreatorAppli getCreatorAppli(UCtx uc);

   /**
    * 
    */
   protected void destroyApp(boolean uncond) throws MIDletStateChangeException {
      coordinator.frameworkExit();
   }

   public CoreFrameworkCtx getCFC() {
      return cac;
   }

   protected IConfigBOC getConfigBOC() {
      return config.getConfigBOC();
   }

   public IConfigCoreData getConfigData() {
      return config.getConfigData();
   }

   /**
    * 
    * @return
    */
   public IConfigCoreDraw getConfigDraw() {
      return config.getConfigDraw();
   }

   public IConfigCoreFramework getConfigFramework() {
      return config.getConfigFramework();
   }

   public IConfigCoreIO getConfigIO() {
      return config.getConfigIO();
   }

   /**
    * 
    */
   public String getConfigIODirectory() {
      return "";
   }

   protected IConfigU getConfigU() {
      return config.getConfigU();
   }

   public IConfigCoreUi getConfigUi() {
      return config.getConfigUi();
   }

   public CoordinatorAbstract getCoordinator() {
      return coordinator;
   }

   /**
    * 
    */
   protected void pauseApp() {
      coordinator.getAppli().amsAppPause();
      coordinator.frameworkPause();
   }

   public void setOSSpecifics(CoreFrameworkCtx hoc) {
   }

   /**
    * Signals the MIDlet that it has entered the Active state.
    * 
    * <p>
    * This method is called by the J2ME framework
    * </p>
    */
   protected void startApp() throws MIDletStateChangeException {

      ICreatorAppli la = getCreatorAppli(uc);
      startAppli(la);
   }

   public void startAppli(ICreatorAppli launcherAppli) {
      coordinator.frameworkStart(launcherAppli);
   }

   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, LauncherJ2meMIDlet.class, 154);
      toStringPrivate(dc);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, LauncherJ2meMIDlet.class, 167);
      toStringPrivate(dc);
   }

   public ILogConfigurator toStringGetLoggingConfig() {
      return new LoggingConfigJ2ME(cac);
   }

   public UCtx toStringGetUCtx() {
      return cac.getUC();
   }

   private void toStringPrivate(Dctx dc) {

   }

   //#enddebug

}
