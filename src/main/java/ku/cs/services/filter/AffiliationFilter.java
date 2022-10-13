package ku.cs.services.filter;

import ku.cs.models.accounts.Account;
import ku.cs.models.accounts.Moderator;

public class AffiliationFilter implements Filterer<Moderator> {

    @Override
    public boolean found(Moderator moderator, String filter) {
        if(moderator.getAffiliation().equals(filter)) return true;
        return false;
    }

    @Override
    public String getName() {
        return "Affiliation";
    }
}
