package com.tels.assignment.presenter;

import android.util.Log;

import com.tels.assignment.R;
import com.tels.assignment.model.Transformer;
import com.tels.assignment.utility.AppConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BattleLogic {

    public static int startBattle(List<Transformer> mTransformersArrayList) {
        ArrayList<Transformer> autobots = new ArrayList<Transformer>();
        ArrayList<Transformer> decepticons = new ArrayList<Transformer>();

        for (Transformer transformer : mTransformersArrayList) {

            if ( transformer.getTeam().contains(AppConstants.TR_ATTR_TEAM_DECEPTICON))
                decepticons.add(transformer);
            else if ( transformer.getTeam().contains(AppConstants.TR_ATTR_TEAM_AUTOBOT))
                autobots.add(transformer);
            else
                Log.e("StartBattle", "The transformer is neither Autobot or Decpeticon: " + transformer.getTeam());
        }

        Collections.sort(decepticons, new TransformerBattleComparator());
        Collections.sort(autobots, new TransformerBattleComparator());

        int battleCount = decepticons.size() > autobots.size() ? autobots.size() : decepticons.size(); // number of battles need to be fought
        int autobotDestroyed = 0;
        int decepticonDestoryed = 0;
        boolean isAllDestroyed = false;

        for (int i = 0; i < battleCount; i ++) {
            // Special Rule: All competitors destroyed & game ends
            if ((autobots.get(i).getName().equals(AppConstants.NAME_OPTIMUS_PRIME) || autobots.get(i).getName().equals(AppConstants.NAME_PREDAKING)) &&
                    (decepticons.get(i).getName().equals(AppConstants.NAME_OPTIMUS_PRIME) || decepticons.get(i).getName().equals(AppConstants.NAME_PREDAKING))) {
                isAllDestroyed = true;
                break;
            }
            else if (autobots.get(i).getName().equals(AppConstants.NAME_OPTIMUS_PRIME) || autobots.get(i).getName().equals(AppConstants.NAME_PREDAKING)) {
                decepticonDestoryed ++;
            }
            else if (decepticons.get(i).getName().equals(AppConstants.NAME_OPTIMUS_PRIME) || decepticons.get(i).getName().equals(AppConstants.NAME_PREDAKING)) {
                autobotDestroyed ++;
            }
            else {
                if ((autobots.get(i).getCourage() >= decepticons.get(i).getCourage() + 4) && (autobots.get(i).getStrength() >= decepticons.get(i).getStrength() + 3)) {
                    decepticonDestoryed++;
                }
                else if ((decepticons.get(i).getCourage() >= autobots.get(i).getCourage() + 4) && (decepticons.get(i).getStrength() >= autobots.get(i).getStrength() + 3)) {
                    autobotDestroyed++;
                }
                else {
                    if (autobots.get(i).getSkill() >= decepticons.get(i).getSkill() + 3) {
                        decepticonDestoryed++;
                    }
                    else if (decepticons.get(i).getSkill() >= autobots.get(i).getSkill() + 3) {
                        autobotDestroyed++;
                    }
                    else {
                        int overallRatingAutobot = autobots.get(i).getStrength() + autobots.get(i).getIntelligence() + autobots.get(i).getSpeed() + autobots.get(i).getEndurance() + autobots.get(i).getFirepower();
                        int overallRatingDecepticon = decepticons.get(i).getStrength() + decepticons.get(i).getIntelligence() + decepticons.get(i).getSpeed()
                                + decepticons.get(i).getEndurance() + decepticons.get(i).getFirepower();

                        if (overallRatingAutobot > overallRatingDecepticon) {
                            decepticonDestoryed++;
                        }
                        else if (overallRatingAutobot < overallRatingDecepticon) {
                            autobotDestroyed++;
                        }
                        else {
                            autobotDestroyed++;
                            decepticonDestoryed++;
                        }
                    }
                }
            }
        }

        int resultTextID = -1;
        if (isAllDestroyed) {
            autobotDestroyed = autobots.size();
            decepticonDestoryed = decepticons.size();

            if (autobotDestroyed > decepticonDestoryed)
                resultTextID = R.string.msg_all_destroyed_decepticon;
            else if (autobotDestroyed < decepticonDestoryed)
                resultTextID = R.string.msg_all_destroyed_autobot;
            else
                resultTextID = R.string.msg_all_destroyed_tie;
        }
        else {
            if (autobotDestroyed > decepticonDestoryed)
                resultTextID = R.string.msg_decepticon_won;
            else if (autobotDestroyed < decepticonDestoryed)
                resultTextID = R.string.msg_autobot_won;
            else
                resultTextID = R.string.msg_tie;
        }

        return resultTextID;
    }

    private static class TransformerBattleComparator implements Comparator<Transformer> {
        @Override
        public int compare(Transformer lhs, Transformer rhs) {
            if (lhs.getRank() == rhs.getRank())
                return 0;
            else if (lhs.getRank() > rhs.getRank())
                return -1;
            else
                return 1;
        }
    }

}
