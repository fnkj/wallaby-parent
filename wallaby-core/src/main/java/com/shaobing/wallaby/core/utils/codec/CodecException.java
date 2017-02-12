package com.shaobing.wallaby.core.utils.codec;


import com.shaobing.wallaby.common.exception.WallabyException;

/**
 * Root exception related to issues during encoding or decoding.
 *
 * @since 0.9
 */
public class CodecException extends WallabyException
{

    /**
     * Creates a new <code>CodecException</code>.
     */
    public CodecException() {
        super();
    }

    /**
     * Creates a new <code>CodecException</code>.
     *
     * @param message the reason for the exception.
     */
    public CodecException(String message) {
        super(message);
    }

    /**
     * Creates a new <code>CodecException</code>.
     *
     * @param cause the underlying cause of the exception.
     */
    public CodecException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new <code>CodecException</code>.
     *
     * @param message the reason for the exception.
     * @param cause   the underlying cause of the exception.
     */
    public CodecException(String message, Throwable cause) {
        super(message, cause);
    }
}
