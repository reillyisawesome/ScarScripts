package scripts.ScarAPI.skills.prayer;

enum PRAYERS {
	THICK_SKIN(1, 4), BURST_OF_STRENGTH(4, 6), CLARITY_OF_THOUGHT(7, 8), SHARP_EYE(
			8, 10), MYSTIC_WILL(9, 12), ROCK_SKIN(10, 14), SUPERHUMAN_STRENGTH(
			13, 16), IMPROVED_REFLEXES(16, 18), RAPID_RESTORE(19, 20), RAPID_HEAL(
			22, 22), PROTECT_ITEMS(25, 24), HAWK_EYE(26, 26), MYSTIC_LORE(
			27, 28), STELL_SKIN(28, 30), ULTIMATE_STRENGTH(31, 32), INCREDIBLE_REFLEXES(
			34, 34), PROTECT_FROM_MAGIC(37, 36), PROTECT_FROM_MISSILES(40,
			38), PROTECT_FROM_MELEE(43, 40), EAGLE_EYE(44, 42), MYSTIC_MIGHT(
			45, 44), RETRIBUTION(46, 46), REDEMPTION(49, 48), SMITE(52, 50), CHIVALRY(
			60, 52), PIETY(70, 54);
	int lvl;
	int index;

	PRAYERS(int lvl, int index) {
		this.lvl = lvl;
		this.index = index;
	}

	public int getRequiredLevel() {
		return lvl;
	}

	public int getChildIndex() {
		return index;
	}

}
