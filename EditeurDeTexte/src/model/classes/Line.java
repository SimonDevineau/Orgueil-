package model.classes;

import model.interfaces.ILine;

/**
 * 10 oct. 2012 - EditeurDeTexte.
 * 
 * @author Simon Devineau & Pierre Reliquet Ecole des Mines de Nantes Major in
 *         Computer and Information System Engineering Line.java
 */
class Line implements ILine {

	/**
	 * The representation of the line as a StringBuffer to avoid creating a new
	 * string each time that a char is added.
	 */
	private StringBuilder _Line;

	private int _CursorLocation = NO_CURSOR;

	/**
	 * Default constructor which creates an empty line
	 */
	Line() {
		_Line = new StringBuilder();
	}

	/**
	 * The constructor to create a line using the CharSequence as starting text.
	 * 
	 * @param sequence
	 *            , the CharSequence which contains the basis.
	 */
	Line(CharSequence sequence) {
		_Line = new StringBuilder(sequence);
	}

	@Override
	public void append(CharSequence content) {
		_Line.append(content);
	}

	@Override
	public boolean hasCursor() {
		return _CursorLocation != NO_CURSOR;
	}

	@Override
	public int getCursorLocation() {
		return _CursorLocation;
	}

	@Override
	public void deleteUnderCursor() {
		if (hasCursor())
			_Line.deleteCharAt(_CursorLocation);
	}

	@Override
	public void addUnderCursor(CharSequence insertion) {
		// TODO vérifier si c'est _CursorLocation ou _CursorLocation+1
		if (hasCursor())
			_Line.insert(_CursorLocation, insertion);
	}
	
	@Override
	public void replaceUnderCursor(CharSequence replacement) {
		if (hasCursor()) {
			// TODO vérifier les index
			// Copying the start of the line (before the cursor)
			StringBuilder tmp = new StringBuilder(_Line.substring(0,
					_CursorLocation));
			
			// Adding the replacement 
			tmp.append(replacement);
			
			// TODO vérifier les index
			// Calculating the new cursor location
			int newCursorLocation = _CursorLocation + replacement.length();
			
			// If the line is longer than the new cursor location we need
			// to append the rest of the line
			if (_Line.length() > newCursorLocation)
				tmp.append(_Line.substring(newCursorLocation));
			
			// We store the new variables.
			_Line = tmp;
			_CursorLocation = newCursorLocation;
		}
	}

}
