public enum StickerType {
    Good, Love, Star;

    static StickerType getType(String text) {
        switch (text) {
            case ":good:":
                return Good;
            case ":love:":
                return Love;
            case ":star:":
                return Star;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case Good:
                return "good";
            case Love:
                return "love";
            case Star:
                return "star";
            default:
                return "";
        }
    }
}