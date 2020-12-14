package pasa.cbentley.framework.core.j2me.coredata.engine;

import javax.microedition.rms.RecordListener;
import javax.microedition.rms.RecordStore;

import pasa.cbentley.framework.core.j2me.coredata.ctx.CoreDataJ2ME;
import pasa.cbentley.framework.coredata.src4.interfaces.IRecordListener;

public class J2MERecordListener implements RecordListener {

   private IRecordListener rl;

   protected final CoreDataJ2ME     dd;

   public J2MERecordListener(CoreDataJ2ME dd, IRecordListener rl) {
      this.dd = dd;
      this.rl = rl;
   }

   public void recordAdded(RecordStore recordStore, int recordId) {
      J2MERecordStore jc = new J2MERecordStore(dd, recordStore);
      rl.recordAdded(jc, recordId);
   }

   public void recordChanged(RecordStore recordStore, int recordId) {
      J2MERecordStore jc = new J2MERecordStore(dd, recordStore);
      rl.recordChanged(jc, recordId);
   }

   public void recordDeleted(RecordStore recordStore, int recordId) {
      J2MERecordStore jc = new J2MERecordStore(dd, recordStore);
      rl.recordDeleted(jc, recordId);
   }

   public IRecordListener getIListener() {
      return rl;
   }

}
