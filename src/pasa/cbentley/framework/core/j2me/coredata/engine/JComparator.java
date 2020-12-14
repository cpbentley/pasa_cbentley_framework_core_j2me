package pasa.cbentley.framework.core.j2me.coredata.engine;

import javax.microedition.rms.RecordComparator;

import pasa.cbentley.framework.coredata.src4.interfaces.IRecordComparator;

public class JComparator implements RecordComparator {

   private IRecordComparator rc;

   public JComparator(IRecordComparator rc) {
      this.rc = rc;
   }

   public int compare(byte[] rec1, byte[] rec2) {
      return rc.compare(rec1, rec2);
   }

}
