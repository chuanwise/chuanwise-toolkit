package cn.chuanwise.utility;

/**
 * Example:
 * There 3 rows and 9 columns. The number inside the cells is the index.
 *                           <--- Column --->
 *            0    1    2    3    4    5    6    7    8
 *    ^    +--------------------------------------------+
 *    |  0 |  0 |  1 |  2 |  3 |  4 |  5 |  6 |  7 |  8 |
 *         |----+----+----+----+----+----+----+----+----|
 *   Row 1 |  9 | 10 | 11 | 12 | 13 | 14 | 15 | 16 | 17 |
 *         |----+----+----+----+----+----+----+----+----|
 *    |  2 | 18 | 19 | 20 | 21 | 22 | 23 | 24 | 25 | 26 |
 *    v    +--------------------------------------------+
 */
public class IndexUtility {
    public static int twoDimensionToOneDimension(int row, int column, int rowNumber, int columnNumber) {
        CheckUtility.checkIndex(row, rowNumber, "row");
        CheckUtility.checkIndex(column, columnNumber, "column");

        return row * rowNumber + column;
    }
}