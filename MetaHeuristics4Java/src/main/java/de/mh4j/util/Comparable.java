/*
 * Copyright 2012   Friedrich Große, Paul Seiferth,
 *                  Sebastian Starroske, Yannik Stein
 *
 * This file is part of MetaHeuristics4Java.
 *
 * MetaHeuristics4Java is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MetaHeuristics4Java is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MetaHeuristics4Java. If not, see <http://www.gnu.org/licenses/>.
 */

package de.mh4j.util;

/**
 * Extended interface of java.lang.Comparable that adds interface constants to
 * remove magic numbers in source code.
 * 
 * @author Friedrich Große
 * 
 * @see java.lang.Comparable
 */
public interface Comparable<T> extends java.lang.Comparable<T> {

    static final int LOWER = -1;
    static final int EQUAL = 0;
    static final int GREATER = 1;

}
