package edu.upc;

import IA.DistFS.Requests;
import IA.DistFS.Servers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Joan on 15/03/2016.
 */
public class FileDistribSystem {

    /**
     * Vector containing all the requests of every user, every request is of size 2 [IdServer, IdFile]
     * Users[] => Requests[] => [IdServer,IdFile]
     */
    public int[][][] system;

    public HashMap<Integer, Integer> idConversion = new HashMap<>();

    /**
     * Creates the initial state
     */
    public FileDistribSystem(Servers servers, Requests requests, int users) {
        initialRandom(servers, requests, users);
    }

    public void initialRandom(Servers servers, Requests requests, int users) {
        //system = new int[users][][];

        ArrayList<ArrayList<ArrayList<Integer>>> st = new ArrayList<>();
        for (int i = 0; i < users; ++i) {
            st.add(new ArrayList<>());
        }

        for (int i = 0; i < requests.size(); ++i) {
            int[] req = requests.getRequest(i);
            int uid = req[0];
            Integer userID = idConversion.get(uid);
            if (userID == null) {
                userID = idConversion.size();
                idConversion.put(uid, idConversion.size());
            }

            Integer fid = req[1];
            Set<Integer> locations = servers.fileLocations(fid);

            Iterator<Integer> itLoc = locations.iterator();
            ArrayList<Integer> query = new ArrayList<>();
            query.add(itLoc.next());
            query.add(fid);

            st.get(userID).add(query);
        }

        system = convertToArray(st);
    }

    public void initialBestServer(Servers servers, Requests requests, int users) {
        ArrayList<ArrayList<ArrayList<Integer>>> st = new ArrayList<>();
        for (int i = 0; i < users; ++i) {
            st.add(new ArrayList<>());
        }
        for (int i = 0; i <requests.size(); ++i) {
            int[] req = requests.getRequest(i);
            int uid = req[0];
            Integer userID = idConversion.get(uid);
            if (userID == null) {
                userID = idConversion.size();
                idConversion.put(uid, idConversion.size());
            }
            uid = userID;

            int fid = req[1];
            Set<Integer> locations = servers.fileLocations(fid);

            int bestSid = -1;
            int bestTime = 10000;

            for (int sid : locations){
                if (bestSid == -1){
                    bestSid=sid;
                }
                else {
                    int time=servers.tranmissionTime(sid,uid);
                    if (time<bestTime){
                        bestTime=time;
                        bestSid=sid;
                    }
                }
            }
            ArrayList<Integer> query = new ArrayList<>();
            query.add(bestSid);
            query.add(fid);

            st.get(uid).add(query);
        }
        system = convertToArray(st);
    }

    /**
     * Converts from an ArrayList to an Array
     *
     * @param st
     */
    private int[][][] convertToArray(ArrayList<ArrayList<ArrayList<Integer>>> st) {
        // Conversion from ArrayList to Array
        int[][][] res = new int[st.size()][][];
        for (int i = 0; i < st.size(); ++i) {
            ArrayList<ArrayList<Integer>> req = st.get(i);
            res[i] = new int[req.size()][2];

            for (int j = 0; j < req.size(); ++j) {
                res[i][j][0] = req.get(j).get(0);
                res[i][j][1] = req.get(j).get(1);
            }
        }
        return res;
    }
}