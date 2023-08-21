package jp.co.icg.base.common;

public class EnumData {

    public enum Status {
        ACTIVE(false),
        DELETED(true),
        READ(false);

        private boolean status;

        Status(boolean status) {
            this.status = status;
        }

        public boolean getStatus() {
            return status;
        }
    }
}
