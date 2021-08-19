package splitwise.services;

import splitwise.models.SplitType;

public class SplitServiceFactory {

    public static ISplitService getInstance(SplitType type) {
        switch (type) {
            case EQUAL:
                return new EqualSplitService();
            case EXACT:
                return new ExactSplitService();
            case PERCENT:
                return new PercentageSplitService();
            default:
                throw new IllegalArgumentException("ERROR: Invalid split type received");
        }
    }

}
