package pasa.cbentley.framework.core.j2me.coredata.engine;

import javax.microedition.rms.InvalidRecordIDException;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotOpenException;

import pasa.cbentley.framework.coredata.src4.ex.StoreException;
import pasa.cbentley.framework.coredata.src4.ex.StoreInvalidIDException;
import pasa.cbentley.framework.coredata.src4.ex.StoreNotOpenException;
import pasa.cbentley.framework.coredata.src4.interfaces.IRecordEnumeration;

public class J2MEEnumeration implements IRecordEnumeration {

   public RecordEnumeration re;

   public J2MEEnumeration(RecordEnumeration re) {
      this.re = re;
   }

   public void destroy() {
      re.destroy();
   }

   public boolean hasNextElement() {
      return re.hasNextElement();
   }

   public boolean hasPreviousElement() {
      return re.hasPreviousElement();
   }

   public boolean isKeptUpdated() {
      return re.isKeptUpdated();
   }

   public void keepUpdated(boolean keepUpdated) {
      re.keepUpdated(keepUpdated);
   }

   public byte[] nextRecord() throws StoreInvalidIDException, StoreNotOpenException, StoreException {
      try {
         return re.nextRecord();
      } catch (InvalidRecordIDException e) {
         throw new StoreInvalidIDException("", e);
      } catch (RecordStoreNotOpenException e) {
         throw new StoreNotOpenException("", e);
      } catch (RecordStoreException e) {
         throw new StoreException("", e);
      }
   }

   public int nextRecordId() throws StoreInvalidIDException {
      try {
         return re.nextRecordId();
      } catch (InvalidRecordIDException e) {
         throw new StoreInvalidIDException("", e);
      }
   }

   public int numRecords() throws StoreInvalidIDException, StoreNotOpenException, StoreException {
      return re.numRecords();
   }

   public byte[] previousRecord() throws StoreInvalidIDException, StoreNotOpenException, StoreException {
      try {
         return re.previousRecord();
      } catch (InvalidRecordIDException e) {
         throw new StoreInvalidIDException("", e);
      } catch (RecordStoreNotOpenException e) {
         throw new StoreNotOpenException("", e);
      } catch (RecordStoreException e) {
         throw new StoreException("", e);
      }
   }

   public int previousRecordId() throws StoreInvalidIDException {
      try {
         return re.previousRecordId();
      } catch (InvalidRecordIDException e) {
         throw new StoreInvalidIDException("", e);
      }
   }

   public void rebuild() {
      re.rebuild();
   }

   public void reset() {
      re.reset();
   }

}
