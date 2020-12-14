package pasa.cbentley.framework.core.j2me.coredata.engine;

import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotFoundException;

import pasa.cbentley.framework.core.j2me.coredata.ctx.CoreDataJ2ME;
import pasa.cbentley.framework.coredata.src4.db.IByteRecordStoreFactory;
import pasa.cbentley.framework.coredata.src4.ex.StoreException;
import pasa.cbentley.framework.coredata.src4.ex.StoreNotFoundException;
import pasa.cbentley.framework.coredata.src4.interfaces.IRecordStore;

public class J2MERMS implements IByteRecordStoreFactory {

   protected final CoreDataJ2ME dd;

   public J2MERMS(CoreDataJ2ME dd) {
      this.dd = dd;
   }

   public void deleteRecordStore(String recordStoreName) throws StoreException {
      try {
         RecordStore.deleteRecordStore(recordStoreName);
      } catch (RecordStoreNotFoundException e) {
         throw new StoreNotFoundException("", e);
      } catch (RecordStoreException e) {
         throw new StoreException("", e);
      }
   }

   public int getBase() {
      return 1;
   }

   public String[] listRecordStores() {
      return RecordStore.listRecordStores();
   }

   public IRecordStore openRecordStore(String recordStoreName, boolean createIfNecessary) throws StoreException, StoreNotFoundException {
      try {
         RecordStore rc = RecordStore.openRecordStore(recordStoreName, createIfNecessary);
         J2MERecordStore js = new J2MERecordStore(dd,rc);
         return js;
      } catch (RecordStoreNotFoundException e) {
         throw new StoreNotFoundException("", e);
      } catch (RecordStoreException e) {
         throw new StoreException("", e);
      }
   }


}
