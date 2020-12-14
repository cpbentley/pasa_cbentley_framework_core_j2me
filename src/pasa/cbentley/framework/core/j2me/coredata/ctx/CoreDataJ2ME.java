package pasa.cbentley.framework.core.j2me.coredata.ctx;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.byteobjects.src4.ctx.BOCtx;
import pasa.cbentley.byteobjects.src4.ctx.IConfigBO;
import pasa.cbentley.framework.core.j2me.coredata.engine.J2MERMS;
import pasa.cbentley.framework.coredata.src4.ctx.CoreDataCtx;
import pasa.cbentley.framework.coredata.src4.ctx.IConfigCoreData;
import pasa.cbentley.framework.coredata.src4.db.IByteRecordStoreFactory;

public class CoreDataJ2ME extends CoreDataCtx {

   public static final int CTX_ID = 400;

   private J2MERMS         rms;

   public CoreDataJ2ME(IConfigCoreData config, BOCtx boc) {
      super(config, boc);
      rms = new J2MERMS(this);
   }

   public IByteRecordStoreFactory getByteRecordStoreFactory() {
      return rms;
   }

   public int getCtxID() {
      return CTX_ID;
   }

   public int getBOCtxSettingSize() {
      return 0;
   }

   protected void matchConfig(IConfigBO config, ByteObject settings) {
      
   }

}
