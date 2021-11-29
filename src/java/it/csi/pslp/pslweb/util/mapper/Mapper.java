/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * The Interface Mapper.
 *
 * @param <A> the generic type
 * @param <B> the generic type
 */
public interface Mapper<A, B> {
	
	/**
	 * Map.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @param out the out
	 * @return the c
	 */
	<C extends B> C map(A in, C out);
	
	/**
	 * Map reverse.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @param out the out
	 * @return the c
	 */
	<C extends A> C mapReverse(B in, C out);
	
	/**
	 * Map valid.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @param out the out
	 * @param now the now
	 * @return the c
	 */
	<C extends B> C mapValid(A in, C out, Date now);
	
	/**
	 * Map valid reverse.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @param out the out
	 * @param now the now
	 * @return the c
	 */
	<C extends A> C mapValidReverse(B in, C out, Date now);
	
	/**
	 * Map nullable.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @param out the out
	 * @return the c
	 */
	<C extends B> C mapNullable(A in, C out);
	
	/**
	 * Map nullable reverse.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @param out the out
	 * @return the c
	 */
	<C extends A> C mapNullableReverse(B in, C out);
	
	/**
	 * Map valid nullable.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @param out the out
	 * @param now the now
	 * @return the c
	 */
	<C extends B> C mapValidNullable(A in, C out, Date now);
	
	/**
	 * Map valid nullable reverse.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @param out the out
	 * @param now the now
	 * @return the c
	 */
	<C extends A> C mapValidNullableReverse(B in, C out, Date now);
	
	/**
	 * Map.
	 *
	 * @param in the in
	 * @return the b
	 */
	B map(A in);
	
	/**
	 * Map reverse.
	 *
	 * @param in the in
	 * @return the a
	 */
	A mapReverse(B in);
	
	/**
	 * Map valid.
	 *
	 * @param in the in
	 * @param now the now
	 * @return the b
	 */
	B mapValid(A in, Date now);
	
	/**
	 * Map valid reverse.
	 *
	 * @param in the in
	 * @param now the now
	 * @return the a
	 */
	A mapValidReverse(B in, Date now);
	
	/**
	 * Map nullable.
	 *
	 * @param in the in
	 * @return the b
	 */
	B mapNullable(A in);
	
	/**
	 * Map nullable reverse.
	 *
	 * @param in the in
	 * @return the a
	 */
	A mapNullableReverse(B in);
	
	/**
	 * Map valid nullable.
	 *
	 * @param in the in
	 * @param now the now
	 * @return the b
	 */
	B mapValidNullable(A in, Date now);
	
	/**
	 * Map valid nullable reverse.
	 *
	 * @param in the in
	 * @param now the now
	 * @return the a
	 */
	A mapValidNullableReverse(B in, Date now);
	
	/**
	 * Map list.
	 *
	 * @param <C> the generic type
	 * @param <D> the generic type
	 * @param in the in
	 * @param out the out
	 * @return the d
	 */
	<C extends Iterable<? extends A>, D extends Collection<? super B>> D mapList(C in, D out);
	
	/**
	 * Map list reverse.
	 *
	 * @param <C> the generic type
	 * @param <D> the generic type
	 * @param in the in
	 * @param out the out
	 * @return the c
	 */
	<C extends Collection<? super A>, D extends Iterable<? extends B>> C mapListReverse(D in, C out);
	
	/**
	 * Map list valid.
	 *
	 * @param <C> the generic type
	 * @param <D> the generic type
	 * @param in the in
	 * @param out the out
	 * @param now the now
	 * @return the d
	 */
	<C extends Iterable<? extends A>, D extends Collection<? super B>> D mapListValid(C in, D out, Date now);
	
	/**
	 * Map list valid reverse.
	 *
	 * @param <C> the generic type
	 * @param <D> the generic type
	 * @param in the in
	 * @param out the out
	 * @param now the now
	 * @return the c
	 */
	<C extends Collection<? super A>, D extends Iterable<? extends B>> C mapListValidReverse(D in, C out, Date now);
	
	/**
	 * Map list.
	 *
	 * @param <C> the generic type
	 * @param <D> the generic type
	 * @param in the in
	 * @param out the out
	 * @return the d
	 */
	<C extends A, D extends Collection<? super B>> D mapList(C[] in, D out);
	
	/**
	 * Map list reverse.
	 *
	 * @param <C> the generic type
	 * @param <D> the generic type
	 * @param in the in
	 * @param out the out
	 * @return the c
	 */
	<C extends Collection<? super A>, D extends B> C mapListReverse(D[] in, C out);
	
	/**
	 * Map list valid.
	 *
	 * @param <C> the generic type
	 * @param <D> the generic type
	 * @param in the in
	 * @param out the out
	 * @param now the now
	 * @return the d
	 */
	<C extends A, D extends Collection<? super B>> D mapListValid(C[] in, D out, Date now);
	
	/**
	 * Map list valid reverse.
	 *
	 * @param <C> the generic type
	 * @param <D> the generic type
	 * @param in the in
	 * @param out the out
	 * @param now the now
	 * @return the c
	 */
	<C extends Collection<? super A>, D extends B> C mapListValidReverse(D[] in, C out, Date now);

	/**
	 * Map list.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @return the list
	 */
	<C extends Iterable<? extends A>> List<B> mapList(C in);
	
	/**
	 * Map list reverse.
	 *
	 * @param <D> the generic type
	 * @param in the in
	 * @return the list
	 */
	<D extends Iterable<? extends B>> List<A> mapListReverse(D in);
	
	/**
	 * Map list valid.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @param now the now
	 * @return the list
	 */
	<C extends Iterable<? extends A>> List<B> mapListValid(C in, Date now);
	
	/**
	 * Map list valid reverse.
	 *
	 * @param <D> the generic type
	 * @param in the in
	 * @param now the now
	 * @return the list
	 */
	<D extends Iterable<? extends B>> List<A> mapListValidReverse(D in, Date now);
	
	/**
	 * Map list.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @return the list
	 */
	<C extends A> List<B> mapList(C[] in);
	
	/**
	 * Map list reverse.
	 *
	 * @param <D> the generic type
	 * @param in the in
	 * @return the list
	 */
	<D extends B> List<A> mapListReverse(D[] in);
	
	/**
	 * Map list valid.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @param now the now
	 * @return the list
	 */
	<C extends A> List<B> mapListValid(C[] in, Date now);
	
	/**
	 * Map list valid reverse.
	 *
	 * @param <D> the generic type
	 * @param in the in
	 * @param now the now
	 * @return the list
	 */
	<D extends B> List<A> mapListValidReverse(D[] in, Date now);
}
