package pasa.cbentley.framework.core.j2me.coredata.engine;

import java.util.Vector;

import javax.microedition.rms.InvalidRecordIDException;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotOpenException;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.framework.core.j2me.coredata.ctx.CoreDataJ2ME;
import pasa.cbentley.framework.coredata.src4.ex.StoreException;
import pasa.cbentley.framework.coredata.src4.ex.StoreFullException;
import pasa.cbentley.framework.coredata.src4.ex.StoreInvalidIDException;
import pasa.cbentley.framework.coredata.src4.ex.StoreNotOpenException;
import pasa.cbentley.framework.coredata.src4.interfaces.IRecordComparator;
import pasa.cbentley.framework.coredata.src4.interfaces.IRecordEnumeration;
import pasa.cbentley.framework.coredata.src4.interfaces.IRecordFilter;
import pasa.cbentley.framework.coredata.src4.interfaces.IRecordListener;
import pasa.cbentley.framework.coredata.src4.interfaces.IRecordStore;

public class J2MERecordStore implements IRecordStore {

   private RecordStore rs;

   private Vector      listeners;

   private Vector      listeners2;

   protected final CoreDataJ2ME dd;

   public J2MERecordStore(CoreDataJ2ME dd, RecordStore rs) {
      this.dd = dd;
      this.rs = rs;
   }

   public int getBase() {
      return 1;
   }

   public void closeRecordStore() throws StoreException {
      try {
         rs.closeRecordStore();
      } catch (RecordStoreNotOpenException e) {
      } catch (RecordStoreException e) {
         throw new StoreException("", e);
      }
   }

   public String getName() throws StoreNotOpenException {
      try {
         return rs.getName();
      } catch (RecordStoreNotOpenException e) {
         throw new StoreNotOpenException("", e);
      }
   }

   public int getVersion() throws StoreNotOpenException {
      try {
         return rs.getVersion();
      } catch (RecordStoreNotOpenException e) {
         throw new StoreNotOpenException("", e);
      }
   }

   public int getNumRecords() throws StoreNotOpenException {
      try {
         return rs.getNumRecords();
      } catch (RecordStoreNotOpenException e) {
         throw new StoreNotOpenException("", e);
      }
   }

   public int getSize() throws StoreNotOpenException {
      try {
         return rs.getSize();
      } catch (RecordStoreNotOpenException e) {
         throw new StoreNotOpenException("", e);
      }
   }

   public int getSizeAvailable() throws StoreNotOpenException {
      try {
         return rs.getSizeAvailable();
      } catch (RecordStoreNotOpenException e) {
         throw new StoreNotOpenException("", e);
      }
   }

   public long getLastModified() throws StoreNotOpenException {
      try {
         return rs.getLastModified();
      } catch (RecordStoreNotOpenException e) {
         throw new StoreNotOpenException("", e);
      }
   }

   public void addRecordListener(IRecordListener listener) {
      if (listeners == null) {
         listeners = new Vector();
         listeners2 = new Vector();
      }
      if (!listeners.contains(listener)) {
         J2MERecordListener je = new J2MERecordListener(dd,listener);
         listeners2.addElement(je);
         rs.addRecordListener(je);
      }
   }

   public synchronized void removeRecordListener(IRecordListener listener) {
      if (listeners == null) {
         return;
      }
      boolean b = listeners.removeElement(listener);
      if (b) {
         for (int i = 0; i < listeners2.size(); i++) {
            J2MERecordListener j2 = (J2MERecordListener) listeners2.elementAt(i);
            if (j2.getIListener() == listener) {
               rs.removeRecordListener(j2);
            }
         }
      }

   }

   public int getNextRecordID() throws StoreNotOpenException, StoreException {
      try {
         int next = rs.getNextRecordID();
         return next - 1;
      } catch (RecordStoreNotOpenException e) {
         throw new StoreNotOpenException("", e);
      } catch (RecordStoreException e) {
         throw new StoreException("", e);
      }
   }

   public int addRecord(byte[] data, int offset, int numBytes) throws StoreNotOpenException, StoreException, StoreFullException {
      try {
         int rid = rs.addRecord(data, offset, numBytes);
         return rid - 1;
      } catch (RecordStoreNotOpenException e) {
         throw new StoreNotOpenException("", e);
      } catch (RecordStoreFullException e) {
         throw new StoreFullException("", e);
      } catch (RecordStoreException e) {
         throw new StoreException("", e);
      }
   }

   public void deleteRecord(int recordId) throws StoreNotOpenException, StoreInvalidIDException, StoreException {
      recordId++; //increment because j2me rms ids start at 1. our framework starts at 0.
      try {
         rs.deleteRecord(recordId);
      } catch (RecordStoreNotOpenException e) {
         throw new StoreNotOpenException("", e);
      } catch (InvalidRecordIDException e) {
         throw new StoreInvalidIDException("", e);
      } catch (RecordStoreException e) {
         throw new StoreException("", e);
      }

   }

   public int getRecordSize(int recordId) throws StoreNotOpenException, StoreInvalidIDException, StoreException {
      recordId++; //increment because j2me rms ids start at 1. our framework starts at 0.
      try {
         return rs.getRecordSize(recordId);
      } catch (RecordStoreNotOpenException e) {
         throw new StoreNotOpenException("", e);
      } catch (InvalidRecordIDException e) {
         throw new StoreInvalidIDException("", e);
      } catch (RecordStoreException e) {
         throw new StoreException("", e);
      }
   }

   public int getRecord(int recordId, byte[] buffer, int offset) throws StoreNotOpenException, StoreInvalidIDException, StoreException {
      recordId++; //increment because j2me rms ids start at 1. our framework starts at 0.
      try {
         return rs.getRecord(recordId, buffer, offset);
      } catch (RecordStoreNotOpenException e) {
         throw new StoreNotOpenException("", e);
      } catch (InvalidRecordIDException e) {
         throw new StoreInvalidIDException("", e);
      } catch (RecordStoreException e) {
         throw new StoreException("", e);
      }
   }

   public byte[] getRecord(int recordId) throws StoreNotOpenException, StoreInvalidIDException, StoreException {
      recordId++; //increment because j2me rms ids start at 1. our framework starts at 0.
      try {
         return rs.getRecord(recordId);
      } catch (RecordStoreNotOpenException e) {
         throw new StoreNotOpenException("", e);
      } catch (InvalidRecordIDException e) {
         throw new StoreInvalidIDException("", e);
      } catch (RecordStoreException e) {
         throw new StoreException("", e);
      }
   }

   public void setMode(int authmode, boolean writable) throws StoreException {
      try {
         rs.setMode(authmode, writable);
      } catch (RecordStoreException e) {
         throw new StoreException("", e);
      }
   }

   public void setRecord(int recordId, byte[] newData, int offset, int numBytes) throws StoreNotOpenException, StoreInvalidIDException, StoreException, StoreFullException {
      recordId++; //increment because j2me rms ids start at 1. our framework starts at 0.
      try {
         rs.setRecord(recordId, newData, offset, numBytes);
      } catch (RecordStoreNotOpenException e) {
         throw new StoreNotOpenException("", e);
      } catch (InvalidRecordIDException e) {
         throw new StoreInvalidIDException("", e);
      } catch (RecordStoreFullException e) {
         throw new StoreFullException("", e);
      } catch (RecordStoreException e) {
         throw new StoreException("", e);
      }
   }

   public IRecordEnumeration enumerateRecords(IRecordFilter filter, IRecordComparator comparator, boolean keepUpdated) throws StoreNotOpenException {
      JFilter jf = new JFilter(filter);
      JComparator jc = new JComparator(comparator);

      RecordEnumeration re;
      try {
         re = rs.enumerateRecords(jf, jc, keepUpdated);
         J2MEEnumeration je = new J2MEEnumeration(re);
         return je;
      } catch (RecordStoreNotOpenException e) {
         throw new StoreNotOpenException("", e);
      }

   }
   
   //#mdebug
   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "J2MERecordStore");
      toStringPrivate(dc);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "J2MERecordStore");
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return dd.getUC();
   }

   //#enddebug
   


}
