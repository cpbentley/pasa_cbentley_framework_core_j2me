package pasa.cbentley.framework.core.j2me.coredata.engine;

import javax.microedition.rms.RecordFilter;

import pasa.cbentley.framework.coredata.src4.interfaces.IRecordFilter;

public class JFilter implements RecordFilter {

   private IRecordFilter rf;

   public JFilter(IRecordFilter rf) {
      this.rf = rf;
   }

   public boolean matches(byte[] candidate) {
      return rf.matches(candidate);
   }

}
