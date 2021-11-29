/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import it.csi.pslp.pslweb.util.Constants;
import it.csi.silos.silcommon.constants.SilCommonConstants;

/**
 * The Class BaseMapper.
 *
 * @param <A> the generic type
 * @param <B> the generic type
 */
public abstract class BaseMapper<A, B> implements Mapper<A, B> {
	
	/** The Constant log. */
	protected static final Logger log = Logger.getLogger(Constants.COMPONENT_NAME);
	
	/**
	 * Map valid.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @param out the out
	 * @param now the now
	 * @return the c
	 */
	@Override
	public <C extends B> C mapValid(A in, C out, Date now) {
		if(isValid(in, now)) {
			map(in, out);
		}
		return out;
	}
	
	/**
	 * Map valid reverse.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @param out the out
	 * @param now the now
	 * @return the c
	 */
	@Override
	public <C extends A> C mapValidReverse(B in, C out, Date now) {
		if(isValidReverse(in, now)) {
			mapReverse(in, out);
		}
		return out;
	}
	
	/**
	 * Map nullable.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @param out the out
	 * @return the c
	 */
	@Override
	public <C extends B> C mapNullable(A in, C out) {
		return in != null ? map(in, out) : null;
	}
	
	/**
	 * Map nullable reverse.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @param out the out
	 * @return the c
	 */
	@Override
	public <C extends A> C mapNullableReverse(B in, C out) {
		return in != null ? mapReverse(in, out) : null;
	}
	
	/**
	 * Map valid nullable.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @param out the out
	 * @param now the now
	 * @return the c
	 */
	@Override
	public <C extends B> C mapValidNullable(A in, C out, Date now) {
		return in != null ? mapValid(in, out, now) : null;
	}
	
	/**
	 * Map valid nullable reverse.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @param out the out
	 * @param now the now
	 * @return the c
	 */
	@Override
	public <C extends A> C mapValidNullableReverse(B in, C out, Date now) {
		return in != null ? mapValidReverse(in, out, now) : null;
	}

	/**
	 * Map.
	 *
	 * @param in the in
	 * @return the b
	 */
	@Override
	public B map(A in) {
		B out = instantiateReverse();
		map(in, out);
		return out;
	}
	
	/**
	 * Map reverse.
	 *
	 * @param in the in
	 * @return the a
	 */
	@Override
	public A mapReverse(B in) {
		A out = instantiate();
		mapReverse(in, out);
		return out;
	}
	
	/**
	 * Map valid.
	 *
	 * @param in the in
	 * @param now the now
	 * @return the b
	 */
	@Override
	public B mapValid(A in, Date now) {
		return isValid(in, now) ? map(in) : null;
	}
	
	/**
	 * Map valid reverse.
	 *
	 * @param in the in
	 * @param now the now
	 * @return the a
	 */
	@Override
	public A mapValidReverse(B in, Date now) {
		return isValidReverse(in, now) ? mapReverse(in) : null;
	}
	
	/**
	 * Map nullable.
	 *
	 * @param in the in
	 * @return the b
	 */
	@Override
	public B mapNullable(A in) {
		return in != null ? map(in) : null;
	}
	
	/**
	 * Map nullable reverse.
	 *
	 * @param in the in
	 * @return the a
	 */
	@Override
	public A mapNullableReverse(B in) {
		return in != null ? mapReverse(in) : null;
	}
	
	/**
	 * Map valid nullable.
	 *
	 * @param in the in
	 * @param now the now
	 * @return the b
	 */
	@Override
	public B mapValidNullable(A in, Date now) {
		return in != null ? mapValid(in, now) : null;
	}
	
	/**
	 * Map valid nullable reverse.
	 *
	 * @param in the in
	 * @param now the now
	 * @return the a
	 */
	@Override
	public A mapValidNullableReverse(B in, Date now) {
		return in != null ? mapValidReverse(in, now) : null;
	}

	/**
	 * Map list.
	 *
	 * @param <C> the generic type
	 * @param <D> the generic type
	 * @param in the in
	 * @param out the out
	 * @return the d
	 */
	@Override
	public <C extends Iterable<? extends A>, D extends Collection<? super B>> D mapList(C in, D out) {
		if(in == null || out == null) {
			return out;
		}
		for(A inst : in) {
			out.add(map(inst));
		}
		return out;
	}
	
	/**
	 * Map list reverse.
	 *
	 * @param <C> the generic type
	 * @param <D> the generic type
	 * @param in the in
	 * @param out the out
	 * @return the c
	 */
	@Override
	public <C extends Collection<? super A>, D extends Iterable<? extends B>> C mapListReverse(D in, C out) {
		if(out == null || in == null) {
			return out;
		}
		for(B inst : in) {
			out.add(mapReverse(inst));
		}
		return out;
	}
	
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
	@Override
	public <C extends Iterable<? extends A>, D extends Collection<? super B>> D mapListValid(C in, D out, Date now) {
		if(in == null || out == null) {
			return out;
		}
		for(A inst : in) {
			if(isValid(inst, now)) {
				out.add(map(inst));
			}
		}
		return out;
	}
	
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
	@Override
	public <C extends Collection<? super A>, D extends Iterable<? extends B>> C mapListValidReverse(D in, C out, Date now) {
		if(out == null || in == null) {
			return out;
		}
		for(B inst : in) {
			if(isValidReverse(inst, now)) {
				out.add(mapReverse(inst));
			}
		}
		return out;
	}
	
	/**
	 * Map list.
	 *
	 * @param <C> the generic type
	 * @param <D> the generic type
	 * @param in the in
	 * @param out the out
	 * @return the d
	 */
	@Override
	public <C extends A, D extends Collection<? super B>> D mapList(C[] in, D out) {
		if(out == null || in == null) {
			return out;
		}
		for(C inst : in) {
			out.add(map(inst));
		}
		return out;
	}
	
	/**
	 * Map list reverse.
	 *
	 * @param <C> the generic type
	 * @param <D> the generic type
	 * @param in the in
	 * @param out the out
	 * @return the c
	 */
	@Override
	public <C extends Collection<? super A>, D extends B> C mapListReverse(D[] in, C out) {
		if(out == null || in == null) {
			return out;
		}
		for(B inst : in) {
			out.add(mapReverse(inst));
		}
		return out;
	}
	
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
	@Override
	public <C extends A, D extends Collection<? super B>> D mapListValid(C[] in, D out, Date now) {
		if(in == null || out == null) {
			return out;
		}
		for(A inst : in) {
			if(isValid(inst, now)) {
				out.add(map(inst));
			}
		}
		return out;
	}
	
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
	@Override
	public <C extends Collection<? super A>, D extends B> C mapListValidReverse(D[] in, C out, Date now) {
		if(out == null || in == null) {
			return out;
		}
		for(B inst : in) {
			if(isValidReverse(inst, now)) {
				out.add(mapReverse(inst));
			}
		}
		return out;
	}

	/**
	 * Map list.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @return the list
	 */
	@Override
	public <C extends Iterable<? extends A>> List<B> mapList(C in) {
		List<B> result = new ArrayList<>();
		mapList(in, result);
		return result;
	}
	
	/**
	 * Map list reverse.
	 *
	 * @param <D> the generic type
	 * @param in the in
	 * @return the list
	 */
	@Override
	public <D extends Iterable<? extends B>> List<A> mapListReverse(D in) {
		List<A> result = new ArrayList<>();
		mapListReverse(in, result);
		return result;
	}
	
	/**
	 * Map list valid.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @param now the now
	 * @return the list
	 */
	@Override
	public <C extends Iterable<? extends A>> List<B> mapListValid(C in, Date now) {
		List<B> result = new ArrayList<>();
		mapListValid(in, result, now);
		return result;
	}
	
	/**
	 * Map list valid reverse.
	 *
	 * @param <D> the generic type
	 * @param in the in
	 * @param now the now
	 * @return the list
	 */
	@Override
	public <D extends Iterable<? extends B>> List<A> mapListValidReverse(D in, Date now) {
		List<A> result = new ArrayList<>();
		mapListValidReverse(in, result, now);
		return result;
	}
	
	/**
	 * Map list.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @return the list
	 */
	@Override
	public <C extends A> List<B> mapList(C[] in) {
		List<B> result = new ArrayList<>();
		mapList(in, result);
		return result;
	}
	
	/**
	 * Map list reverse.
	 *
	 * @param <D> the generic type
	 * @param in the in
	 * @return the list
	 */
	@Override
	public <D extends B> List<A> mapListReverse(D[] in) {
		List<A> result = new ArrayList<>();
		mapListReverse(in, result);
		return result;
	}
	
	/**
	 * Map list valid.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @param now the now
	 * @return the list
	 */
	@Override
	public <C extends A> List<B> mapListValid(C[] in, Date now) {
		List<B> result = new ArrayList<>();
		mapListValid(in, result, now);
		return result;
	}
	
	/**
	 * Map list valid reverse.
	 *
	 * @param <D> the generic type
	 * @param in the in
	 * @param now the now
	 * @return the list
	 */
	@Override
	public <D extends B> List<A> mapListValidReverse(D[] in, Date now) {
		List<A> result = new ArrayList<>();
		mapListValidReverse(in, result, now);
		return result;
	}
	
	/**
	 * Instantiate.
	 *
	 * @return the a
	 */
	protected abstract A instantiate();
	
	/**
	 * Instantiate reverse.
	 *
	 * @return the b
	 */
	protected abstract B instantiateReverse();
	
	/**
	 * Checks if is valid.
	 *
	 * @param in the in
	 * @param now the now
	 * @return true, if is valid
	 */
	protected boolean isValid(A in, Date now) {
		return true;
	}
	
	/**
	 * Checks if is valid reverse.
	 *
	 * @param in the in
	 * @param now the now
	 * @return true, if is valid reverse
	 */
	protected boolean isValidReverse(B in, Date now) {
		return true;
	}
	
	/**
	 * To si no.
	 *
	 * @param value the value
	 * @return the string
	 */
	protected String toSiNo(Boolean value) {
		return Boolean.TRUE.equals(value)?SilCommonConstants.SI:SilCommonConstants.NO;
	}
}
