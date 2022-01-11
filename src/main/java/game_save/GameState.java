package main.java.game_save;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.Serializable;

/**
 * A gameState is a list of gameStateElements.
 */
public class GameState implements Iterable<Object>, Serializable{
    private ArrayList<Object> saveState;

    public GameState() {
        saveState = new ArrayList<>();
    }

    /**
     * Adds a new gameStateElement with gameStateData and parameter type.
     *
     * @param gameStateData  data for the gameStateElement
     */

    public void addGameStateElement(Object gameStateData){
        saveState.add(gameStateData);

    }
    /**
     * Set the saveState.
     */

    public void setSaveState(ArrayList<Object> saveList){
        this.saveState = saveList;

    }

    /**
     * Returns the saveState.
     */
    public ArrayList<Object> getSaveState(){
        return saveState;
    }

    /**
     * Returns the number of elements in saveState.
     *
     * @return the number of gameStateElements in this gameState.
     */
    public int getSize() {
        return saveState.size();
    }



    /**
     * Returns an iterator for this gameState.
     *
     * @return an iterator for this gameState.
     */
    @Override
    public Iterator<Object> iterator() {
        return new gameStateIterator();
    }

    private class gameStateIterator implements Iterator<Object> {
        private int currentIndex = 0;


        /**
         * Returns true if the iteration has more elements.
         *
         * @return true if the iteration has more elements.
         */
        @Override
        public boolean hasNext() {
            return currentIndex < saveState.size();
        }


        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration.
         */
        @Override
        public Object next() {
            return saveState.get(currentIndex++);
        }


        /**
         * Removes from the underlying collection the last element returned by the iterator.
         */
        @Override
        public void remove() {
            saveState.remove(currentIndex);
        }

    }


}