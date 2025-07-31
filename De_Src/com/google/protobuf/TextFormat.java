/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.MessageReflection;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class TextFormat {
    private static final Logger logger = Logger.getLogger(TextFormat.class.getName());
    private static final Printer DEFAULT_PRINTER = new Printer();
    private static final Printer SINGLE_LINE_PRINTER = Printer.access$100(new Printer(), true);
    private static final Printer UNICODE_PRINTER = Printer.access$200(new Printer(), false);
    private static final Parser PARSER = Parser.newBuilder().build();

    private TextFormat() {
    }

    public static void print(MessageOrBuilder message, Appendable output) throws IOException {
        TextFormat.DEFAULT_PRINTER.print(message, new TextGenerator(output));
    }

    public static void print(UnknownFieldSet fields, Appendable output) throws IOException {
        TextFormat.DEFAULT_PRINTER.printUnknownFields(fields, new TextGenerator(output));
    }

    public static void printUnicode(MessageOrBuilder message, Appendable output) throws IOException {
        TextFormat.UNICODE_PRINTER.print(message, new TextGenerator(output));
    }

    public static void printUnicode(UnknownFieldSet fields, Appendable output) throws IOException {
        TextFormat.UNICODE_PRINTER.printUnknownFields(fields, new TextGenerator(output));
    }

    public static String shortDebugString(MessageOrBuilder message) {
        try {
            StringBuilder sb = new StringBuilder();
            TextFormat.SINGLE_LINE_PRINTER.print(message, new TextGenerator(sb));
            return sb.toString().trim();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String shortDebugString(UnknownFieldSet fields) {
        try {
            StringBuilder sb = new StringBuilder();
            TextFormat.SINGLE_LINE_PRINTER.printUnknownFields(fields, new TextGenerator(sb));
            return sb.toString().trim();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String printToString(MessageOrBuilder message) {
        try {
            StringBuilder text = new StringBuilder();
            TextFormat.print(message, (Appendable)text);
            return text.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String printToString(UnknownFieldSet fields) {
        try {
            StringBuilder text = new StringBuilder();
            TextFormat.print(fields, (Appendable)text);
            return text.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String printToUnicodeString(MessageOrBuilder message) {
        try {
            StringBuilder text = new StringBuilder();
            TextFormat.UNICODE_PRINTER.print(message, new TextGenerator(text));
            return text.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String printToUnicodeString(UnknownFieldSet fields) {
        try {
            StringBuilder text = new StringBuilder();
            TextFormat.UNICODE_PRINTER.printUnknownFields(fields, new TextGenerator(text));
            return text.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void printField(Descriptors.FieldDescriptor field, Object value, Appendable output) throws IOException {
        TextFormat.DEFAULT_PRINTER.printField(field, value, new TextGenerator(output));
    }

    public static String printFieldToString(Descriptors.FieldDescriptor field, Object value) {
        try {
            StringBuilder text = new StringBuilder();
            TextFormat.printField(field, value, text);
            return text.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void printFieldValue(Descriptors.FieldDescriptor field, Object value, Appendable output) throws IOException {
        TextFormat.DEFAULT_PRINTER.printFieldValue(field, value, new TextGenerator(output));
    }

    public static void printUnknownFieldValue(int tag, Object value, Appendable output) throws IOException {
        TextFormat.printUnknownFieldValue(tag, value, new TextGenerator(output));
    }

    private static void printUnknownFieldValue(int tag, Object value, TextGenerator generator) throws IOException {
        switch (WireFormat.getTagWireType(tag)) {
            case 0: {
                generator.print(TextFormat.unsignedToString((Long)value));
                break;
            }
            case 5: {
                generator.print(String.format((Locale)null, "0x%08x", (Integer)value));
                break;
            }
            case 1: {
                generator.print(String.format((Locale)null, "0x%016x", (Long)value));
                break;
            }
            case 2: {
                generator.print("\"");
                generator.print(TextFormat.escapeBytes((ByteString)value));
                generator.print("\"");
                break;
            }
            case 3: {
                TextFormat.DEFAULT_PRINTER.printUnknownFields((UnknownFieldSet)value, generator);
                break;
            }
            default: {
                int n = tag;
                throw new IllegalArgumentException(new StringBuilder(20).append("Bad tag: ").append(n).toString());
            }
        }
    }

    public static String unsignedToString(int value) {
        if (value >= 0) {
            return Integer.toString(value);
        }
        return Long.toString((long)value & 0xFFFFFFFFL);
    }

    public static String unsignedToString(long value) {
        if (value >= 0L) {
            return Long.toString(value);
        }
        return BigInteger.valueOf(value & Long.MAX_VALUE).setBit(63).toString();
    }

    public static Parser getParser() {
        return PARSER;
    }

    public static void merge(Readable input, Message.Builder builder) throws IOException {
        PARSER.merge(input, builder);
    }

    public static void merge(CharSequence input, Message.Builder builder) throws ParseException {
        PARSER.merge(input, builder);
    }

    public static void merge(Readable input, ExtensionRegistry extensionRegistry, Message.Builder builder) throws IOException {
        PARSER.merge(input, extensionRegistry, builder);
    }

    public static void merge(CharSequence input, ExtensionRegistry extensionRegistry, Message.Builder builder) throws ParseException {
        PARSER.merge(input, extensionRegistry, builder);
    }

    private static String escapeBytes(ByteSequence input) {
        StringBuilder builder = new StringBuilder(input.size());
        block12: for (int i = 0; i < input.size(); ++i) {
            byte b = input.byteAt(i);
            switch (b) {
                case 7: {
                    builder.append("\\a");
                    continue block12;
                }
                case 8: {
                    builder.append("\\b");
                    continue block12;
                }
                case 12: {
                    builder.append("\\f");
                    continue block12;
                }
                case 10: {
                    builder.append("\\n");
                    continue block12;
                }
                case 13: {
                    builder.append("\\r");
                    continue block12;
                }
                case 9: {
                    builder.append("\\t");
                    continue block12;
                }
                case 11: {
                    builder.append("\\v");
                    continue block12;
                }
                case 92: {
                    builder.append("\\\\");
                    continue block12;
                }
                case 39: {
                    builder.append("\\'");
                    continue block12;
                }
                case 34: {
                    builder.append("\\\"");
                    continue block12;
                }
                default: {
                    if (b >= 32) {
                        builder.append((char)b);
                        continue block12;
                    }
                    builder.append('\\');
                    builder.append((char)(48 + (b >>> 6 & 3)));
                    builder.append((char)(48 + (b >>> 3 & 7)));
                    builder.append((char)(48 + (b & 7)));
                }
            }
        }
        return builder.toString();
    }

    static String escapeBytes(final ByteString input) {
        return TextFormat.escapeBytes(new ByteSequence(){

            public int size() {
                return input.size();
            }

            public byte byteAt(int offset) {
                return input.byteAt(offset);
            }
        });
    }

    static String escapeBytes(final byte[] input) {
        return TextFormat.escapeBytes(new ByteSequence(){

            public int size() {
                return input.length;
            }

            public byte byteAt(int offset) {
                return input[offset];
            }
        });
    }

    static ByteString unescapeBytes(CharSequence charString) throws InvalidEscapeSequenceException {
        ByteString input = ByteString.copyFromUtf8(charString.toString());
        byte[] result = new byte[input.size()];
        int pos = 0;
        for (int i = 0; i < input.size(); ++i) {
            byte c = input.byteAt(i);
            if (c == 92) {
                if (i + 1 < input.size()) {
                    int code;
                    if (TextFormat.isOctal(c = input.byteAt(++i))) {
                        code = TextFormat.digitValue(c);
                        if (i + 1 < input.size() && TextFormat.isOctal(input.byteAt(i + 1))) {
                            code = code * 8 + TextFormat.digitValue(input.byteAt(++i));
                        }
                        if (i + 1 < input.size() && TextFormat.isOctal(input.byteAt(i + 1))) {
                            code = code * 8 + TextFormat.digitValue(input.byteAt(++i));
                        }
                        result[pos++] = (byte)code;
                        continue;
                    }
                    switch (c) {
                        case 97: {
                            result[pos++] = 7;
                            break;
                        }
                        case 98: {
                            result[pos++] = 8;
                            break;
                        }
                        case 102: {
                            result[pos++] = 12;
                            break;
                        }
                        case 110: {
                            result[pos++] = 10;
                            break;
                        }
                        case 114: {
                            result[pos++] = 13;
                            break;
                        }
                        case 116: {
                            result[pos++] = 9;
                            break;
                        }
                        case 118: {
                            result[pos++] = 11;
                            break;
                        }
                        case 92: {
                            result[pos++] = 92;
                            break;
                        }
                        case 39: {
                            result[pos++] = 39;
                            break;
                        }
                        case 34: {
                            result[pos++] = 34;
                            break;
                        }
                        case 120: {
                            code = 0;
                            if (i + 1 >= input.size() || !TextFormat.isHex(input.byteAt(i + 1))) {
                                throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\x' with no digits");
                            }
                            code = TextFormat.digitValue(input.byteAt(++i));
                            if (i + 1 < input.size() && TextFormat.isHex(input.byteAt(i + 1))) {
                                code = code * 16 + TextFormat.digitValue(input.byteAt(++i));
                            }
                            result[pos++] = (byte)code;
                            break;
                        }
                        default: {
                            char c2 = (char)c;
                            throw new InvalidEscapeSequenceException(new StringBuilder(29).append("Invalid escape sequence: '\\").append(c2).append("'").toString());
                        }
                    }
                    continue;
                }
                throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\' at end of string.");
            }
            result[pos++] = c;
        }
        return ByteString.copyFrom(result, 0, pos);
    }

    static String escapeText(String input) {
        return TextFormat.escapeBytes(ByteString.copyFromUtf8(input));
    }

    public static String escapeDoubleQuotesAndBackslashes(String input) {
        return input.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    static String unescapeText(String input) throws InvalidEscapeSequenceException {
        return TextFormat.unescapeBytes(input).toStringUtf8();
    }

    private static boolean isOctal(byte c) {
        return 48 <= c && c <= 55;
    }

    private static boolean isHex(byte c) {
        return 48 <= c && c <= 57 || 97 <= c && c <= 102 || 65 <= c && c <= 70;
    }

    private static int digitValue(byte c) {
        if (48 <= c && c <= 57) {
            return c - 48;
        }
        if (97 <= c && c <= 122) {
            return c - 97 + 10;
        }
        return c - 65 + 10;
    }

    static int parseInt32(String text) throws NumberFormatException {
        return (int)TextFormat.parseInteger(text, true, false);
    }

    static int parseUInt32(String text) throws NumberFormatException {
        return (int)TextFormat.parseInteger(text, false, false);
    }

    static long parseInt64(String text) throws NumberFormatException {
        return TextFormat.parseInteger(text, true, true);
    }

    static long parseUInt64(String text) throws NumberFormatException {
        return TextFormat.parseInteger(text, false, true);
    }

    private static long parseInteger(String text, boolean isSigned, boolean isLong) throws NumberFormatException {
        int pos = 0;
        boolean negative = false;
        if (text.startsWith("-", pos)) {
            if (!isSigned) {
                String string = String.valueOf(text);
                throw new NumberFormatException(string.length() != 0 ? "Number must be positive: ".concat(string) : new String("Number must be positive: "));
            }
            ++pos;
            negative = true;
        }
        int radix = 10;
        if (text.startsWith("0x", pos)) {
            pos += 2;
            radix = 16;
        } else if (text.startsWith("0", pos)) {
            radix = 8;
        }
        String numberText = text.substring(pos);
        long result = 0L;
        if (numberText.length() < 16) {
            result = Long.parseLong(numberText, radix);
            if (negative) {
                result = -result;
            }
            if (!isLong) {
                if (isSigned) {
                    if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
                        String string = String.valueOf(text);
                        throw new NumberFormatException(string.length() != 0 ? "Number out of range for 32-bit signed integer: ".concat(string) : new String("Number out of range for 32-bit signed integer: "));
                    }
                } else if (result >= 0x100000000L || result < 0L) {
                    String string = String.valueOf(text);
                    throw new NumberFormatException(string.length() != 0 ? "Number out of range for 32-bit unsigned integer: ".concat(string) : new String("Number out of range for 32-bit unsigned integer: "));
                }
            }
        } else {
            BigInteger bigValue = new BigInteger(numberText, radix);
            if (negative) {
                bigValue = bigValue.negate();
            }
            if (!isLong) {
                if (isSigned) {
                    if (bigValue.bitLength() > 31) {
                        String string = String.valueOf(text);
                        throw new NumberFormatException(string.length() != 0 ? "Number out of range for 32-bit signed integer: ".concat(string) : new String("Number out of range for 32-bit signed integer: "));
                    }
                } else if (bigValue.bitLength() > 32) {
                    String string = String.valueOf(text);
                    throw new NumberFormatException(string.length() != 0 ? "Number out of range for 32-bit unsigned integer: ".concat(string) : new String("Number out of range for 32-bit unsigned integer: "));
                }
            } else if (isSigned) {
                if (bigValue.bitLength() > 63) {
                    String string = String.valueOf(text);
                    throw new NumberFormatException(string.length() != 0 ? "Number out of range for 64-bit signed integer: ".concat(string) : new String("Number out of range for 64-bit signed integer: "));
                }
            } else if (bigValue.bitLength() > 64) {
                String string = String.valueOf(text);
                throw new NumberFormatException(string.length() != 0 ? "Number out of range for 64-bit unsigned integer: ".concat(string) : new String("Number out of range for 64-bit unsigned integer: "));
            }
            result = bigValue.longValue();
        }
        return result;
    }

    static class InvalidEscapeSequenceException
    extends IOException {
        private static final long serialVersionUID = -8164033650142593304L;

        InvalidEscapeSequenceException(String description) {
            super(description);
        }
    }

    private static interface ByteSequence {
        public int size();

        public byte byteAt(int var1);
    }

    public static class Parser {
        private final boolean allowUnknownFields;
        private final SingularOverwritePolicy singularOverwritePolicy;
        private static final int BUFFER_SIZE = 4096;

        private Parser(boolean allowUnknownFields, SingularOverwritePolicy singularOverwritePolicy) {
            this.allowUnknownFields = allowUnknownFields;
            this.singularOverwritePolicy = singularOverwritePolicy;
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public void merge(Readable input, Message.Builder builder) throws IOException {
            this.merge(input, ExtensionRegistry.getEmptyRegistry(), builder);
        }

        public void merge(CharSequence input, Message.Builder builder) throws ParseException {
            this.merge(input, ExtensionRegistry.getEmptyRegistry(), builder);
        }

        public void merge(Readable input, ExtensionRegistry extensionRegistry, Message.Builder builder) throws IOException {
            this.merge(Parser.toStringBuilder(input), extensionRegistry, builder);
        }

        private static StringBuilder toStringBuilder(Readable input) throws IOException {
            int n;
            StringBuilder text = new StringBuilder();
            CharBuffer buffer = CharBuffer.allocate(4096);
            while ((n = input.read(buffer)) != -1) {
                buffer.flip();
                text.append(buffer, 0, n);
            }
            return text;
        }

        public void merge(CharSequence input, ExtensionRegistry extensionRegistry, Message.Builder builder) throws ParseException {
            Tokenizer tokenizer = new Tokenizer(input);
            MessageReflection.BuilderAdapter target = new MessageReflection.BuilderAdapter(builder);
            while (!tokenizer.atEnd()) {
                this.mergeField(tokenizer, extensionRegistry, target);
            }
        }

        private void mergeField(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget target) throws ParseException {
            String string;
            CharSequence name;
            Descriptors.FieldDescriptor field = null;
            Descriptors.Descriptor type = target.getDescriptorForType();
            ExtensionRegistry.ExtensionInfo extension = null;
            if (tokenizer.tryConsume("[")) {
                name = new StringBuilder(tokenizer.consumeIdentifier());
                while (tokenizer.tryConsume(".")) {
                    ((StringBuilder)name).append('.');
                    ((StringBuilder)name).append(tokenizer.consumeIdentifier());
                }
                extension = target.findExtensionByName(extensionRegistry, ((StringBuilder)name).toString());
                if (extension == null) {
                    if (!this.allowUnknownFields) {
                        String string2 = String.valueOf(String.valueOf(name));
                        throw tokenizer.parseExceptionPreviousToken(new StringBuilder(48 + string2.length()).append("Extension \"").append(string2).append("\" not found in the ExtensionRegistry.").toString());
                    }
                    string = String.valueOf(String.valueOf(name));
                    logger.warning(new StringBuilder(48 + string.length()).append("Extension \"").append(string).append("\" not found in the ExtensionRegistry.").toString());
                } else {
                    if (extension.descriptor.getContainingType() != type) {
                        String string3 = String.valueOf(String.valueOf(name));
                        String string4 = String.valueOf(String.valueOf(type.getFullName()));
                        throw tokenizer.parseExceptionPreviousToken(new StringBuilder(45 + string3.length() + string4.length()).append("Extension \"").append(string3).append("\" does not extend message type \"").append(string4).append("\".").toString());
                    }
                    field = extension.descriptor;
                }
                tokenizer.consume("]");
            } else {
                String lowerName;
                name = tokenizer.consumeIdentifier();
                field = type.findFieldByName((String)name);
                if (field == null && (field = type.findFieldByName(lowerName = ((String)name).toLowerCase(Locale.US))) != null && field.getType() != Descriptors.FieldDescriptor.Type.GROUP) {
                    field = null;
                }
                if (field != null && field.getType() == Descriptors.FieldDescriptor.Type.GROUP && !field.getMessageType().getName().equals(name)) {
                    field = null;
                }
                if (field == null) {
                    if (!this.allowUnknownFields) {
                        string = String.valueOf(String.valueOf(type.getFullName()));
                        String string5 = String.valueOf(String.valueOf(name));
                        throw tokenizer.parseExceptionPreviousToken(new StringBuilder(38 + string.length() + string5.length()).append("Message type \"").append(string).append("\" has no field named \"").append(string5).append("\".").toString());
                    }
                    string = String.valueOf(String.valueOf(type.getFullName()));
                    String string6 = String.valueOf(String.valueOf(name));
                    logger.warning(new StringBuilder(38 + string.length() + string6.length()).append("Message type \"").append(string).append("\" has no field named \"").append(string6).append("\".").toString());
                }
            }
            if (field == null) {
                if (tokenizer.tryConsume(":") && !tokenizer.lookingAt("{") && !tokenizer.lookingAt("<")) {
                    this.skipFieldValue(tokenizer);
                } else {
                    this.skipFieldMessage(tokenizer);
                }
                return;
            }
            if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                tokenizer.tryConsume(":");
            } else {
                tokenizer.consume(":");
            }
            if (field.isRepeated() && tokenizer.tryConsume("[")) {
                while (true) {
                    this.consumeFieldValue(tokenizer, extensionRegistry, target, field, extension);
                    if (!tokenizer.tryConsume("]")) {
                        tokenizer.consume(",");
                        continue;
                    }
                    break;
                }
            } else {
                this.consumeFieldValue(tokenizer, extensionRegistry, target, field, extension);
            }
        }

        private void consumeFieldValue(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget target, Descriptors.FieldDescriptor field, ExtensionRegistry.ExtensionInfo extension) throws ParseException {
            Object enumType;
            Object value = null;
            if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                String endToken;
                if (tokenizer.tryConsume("<")) {
                    endToken = ">";
                } else {
                    tokenizer.consume("{");
                    endToken = "}";
                }
                MessageReflection.MergeTarget subField = target.newMergeTargetForField(field, extension == null ? null : extension.defaultInstance);
                while (!tokenizer.tryConsume(endToken)) {
                    if (tokenizer.atEnd()) {
                        String string = String.valueOf(String.valueOf(endToken));
                        throw tokenizer.parseException(new StringBuilder(12 + string.length()).append("Expected \"").append(string).append("\".").toString());
                    }
                    this.mergeField(tokenizer, extensionRegistry, subField);
                }
                value = subField.finish();
            } else {
                switch (field.getType()) {
                    case INT32: 
                    case SINT32: 
                    case SFIXED32: {
                        value = tokenizer.consumeInt32();
                        break;
                    }
                    case INT64: 
                    case SINT64: 
                    case SFIXED64: {
                        value = tokenizer.consumeInt64();
                        break;
                    }
                    case UINT32: 
                    case FIXED32: {
                        value = tokenizer.consumeUInt32();
                        break;
                    }
                    case UINT64: 
                    case FIXED64: {
                        value = tokenizer.consumeUInt64();
                        break;
                    }
                    case FLOAT: {
                        value = Float.valueOf(tokenizer.consumeFloat());
                        break;
                    }
                    case DOUBLE: {
                        value = tokenizer.consumeDouble();
                        break;
                    }
                    case BOOL: {
                        value = tokenizer.consumeBoolean();
                        break;
                    }
                    case STRING: {
                        value = tokenizer.consumeString();
                        break;
                    }
                    case BYTES: {
                        value = tokenizer.consumeByteString();
                        break;
                    }
                    case ENUM: {
                        enumType = field.getEnumType();
                        if (tokenizer.lookingAtInteger()) {
                            int number = tokenizer.consumeInt32();
                            value = ((Descriptors.EnumDescriptor)enumType).findValueByNumber(number);
                            if (value != null) break;
                            String string = String.valueOf(String.valueOf(((Descriptors.EnumDescriptor)enumType).getFullName()));
                            int n = number;
                            throw tokenizer.parseExceptionPreviousToken(new StringBuilder(50 + string.length()).append("Enum type \"").append(string).append("\" has no value with number ").append(n).append(".").toString());
                        }
                        String id = tokenizer.consumeIdentifier();
                        value = ((Descriptors.EnumDescriptor)enumType).findValueByName(id);
                        if (value != null) break;
                        String string = String.valueOf(String.valueOf(((Descriptors.EnumDescriptor)enumType).getFullName()));
                        String string2 = String.valueOf(String.valueOf(id));
                        throw tokenizer.parseExceptionPreviousToken(new StringBuilder(35 + string.length() + string2.length()).append("Enum type \"").append(string).append("\" has no value named \"").append(string2).append("\".").toString());
                    }
                    case MESSAGE: 
                    case GROUP: {
                        throw new RuntimeException("Can't get here.");
                    }
                }
            }
            if (field.isRepeated()) {
                target.addRepeatedField(field, value);
            } else {
                if (this.singularOverwritePolicy == SingularOverwritePolicy.FORBID_SINGULAR_OVERWRITES && target.hasField(field)) {
                    enumType = String.valueOf(String.valueOf(field.getFullName()));
                    throw tokenizer.parseExceptionPreviousToken(new StringBuilder(44 + ((String)enumType).length()).append("Non-repeated field \"").append((String)enumType).append("\" cannot be overwritten.").toString());
                }
                if (this.singularOverwritePolicy == SingularOverwritePolicy.FORBID_SINGULAR_OVERWRITES && field.getContainingOneof() != null && target.hasOneof(field.getContainingOneof())) {
                    Descriptors.OneofDescriptor oneof = field.getContainingOneof();
                    String string = String.valueOf(String.valueOf(field.getFullName()));
                    String string3 = String.valueOf(String.valueOf(target.getOneofFieldDescriptor(oneof).getFullName()));
                    String string4 = String.valueOf(String.valueOf(oneof.getName()));
                    throw tokenizer.parseExceptionPreviousToken(new StringBuilder(70 + string.length() + string3.length() + string4.length()).append("Field \"").append(string).append("\" is specified along with field \"").append(string3).append("\", another member of oneof \"").append(string4).append("\".").toString());
                }
                target.setField(field, value);
            }
        }

        private void skipField(Tokenizer tokenizer) throws ParseException {
            if (tokenizer.tryConsume("[")) {
                do {
                    tokenizer.consumeIdentifier();
                } while (tokenizer.tryConsume("."));
                tokenizer.consume("]");
            } else {
                tokenizer.consumeIdentifier();
            }
            if (tokenizer.tryConsume(":") && !tokenizer.lookingAt("<") && !tokenizer.lookingAt("{")) {
                this.skipFieldValue(tokenizer);
            } else {
                this.skipFieldMessage(tokenizer);
            }
            if (!tokenizer.tryConsume(";")) {
                tokenizer.tryConsume(",");
            }
        }

        private void skipFieldMessage(Tokenizer tokenizer) throws ParseException {
            String delimiter;
            if (tokenizer.tryConsume("<")) {
                delimiter = ">";
            } else {
                tokenizer.consume("{");
                delimiter = "}";
            }
            while (!tokenizer.lookingAt(">") && !tokenizer.lookingAt("}")) {
                this.skipField(tokenizer);
            }
            tokenizer.consume(delimiter);
        }

        private void skipFieldValue(Tokenizer tokenizer) throws ParseException {
            if (tokenizer.tryConsumeString()) {
                while (tokenizer.tryConsumeString()) {
                }
                return;
            }
            if (!(tokenizer.tryConsumeIdentifier() || tokenizer.tryConsumeInt64() || tokenizer.tryConsumeUInt64() || tokenizer.tryConsumeDouble() || tokenizer.tryConsumeFloat())) {
                String string = String.valueOf(tokenizer.currentToken);
                throw tokenizer.parseException(string.length() != 0 ? "Invalid field value: ".concat(string) : new String("Invalid field value: "));
            }
        }

        public static class Builder {
            private boolean allowUnknownFields = false;
            private SingularOverwritePolicy singularOverwritePolicy = SingularOverwritePolicy.ALLOW_SINGULAR_OVERWRITES;

            public Builder setSingularOverwritePolicy(SingularOverwritePolicy p) {
                this.singularOverwritePolicy = p;
                return this;
            }

            public Parser build() {
                return new Parser(this.allowUnknownFields, this.singularOverwritePolicy);
            }
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        public static enum SingularOverwritePolicy {
            ALLOW_SINGULAR_OVERWRITES,
            FORBID_SINGULAR_OVERWRITES;

        }
    }

    public static class ParseException
    extends IOException {
        private static final long serialVersionUID = 3196188060225107702L;
        private final int line;
        private final int column;

        public ParseException(String message) {
            this(-1, -1, message);
        }

        public ParseException(int line, int column, String message) {
            String string = String.valueOf(String.valueOf(Integer.toString(line)));
            int n = column;
            String string2 = String.valueOf(String.valueOf(message));
            super(new StringBuilder(14 + string.length() + string2.length()).append(string).append(":").append(n).append(": ").append(string2).toString());
            this.line = line;
            this.column = column;
        }

        public int getLine() {
            return this.line;
        }

        public int getColumn() {
            return this.column;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static final class Tokenizer {
        private final CharSequence text;
        private final Matcher matcher;
        private String currentToken;
        private int pos = 0;
        private int line = 0;
        private int column = 0;
        private int previousLine = 0;
        private int previousColumn = 0;
        private static final Pattern WHITESPACE = Pattern.compile("(\\s|(#.*$))++", 8);
        private static final Pattern TOKEN = Pattern.compile("[a-zA-Z_][0-9a-zA-Z_+-]*+|[.]?[0-9+-][0-9a-zA-Z_.+-]*+|\"([^\"\n\\\\]|\\\\.)*+(\"|\\\\?$)|'([^'\n\\\\]|\\\\.)*+('|\\\\?$)", 8);
        private static final Pattern DOUBLE_INFINITY = Pattern.compile("-?inf(inity)?", 2);
        private static final Pattern FLOAT_INFINITY = Pattern.compile("-?inf(inity)?f?", 2);
        private static final Pattern FLOAT_NAN = Pattern.compile("nanf?", 2);

        private Tokenizer(CharSequence text) {
            this.text = text;
            this.matcher = WHITESPACE.matcher(text);
            this.skipWhitespace();
            this.nextToken();
        }

        public boolean atEnd() {
            return this.currentToken.length() == 0;
        }

        public void nextToken() {
            this.previousLine = this.line;
            this.previousColumn = this.column;
            while (this.pos < this.matcher.regionStart()) {
                if (this.text.charAt(this.pos) == '\n') {
                    ++this.line;
                    this.column = 0;
                } else {
                    ++this.column;
                }
                ++this.pos;
            }
            if (this.matcher.regionStart() == this.matcher.regionEnd()) {
                this.currentToken = "";
            } else {
                this.matcher.usePattern(TOKEN);
                if (this.matcher.lookingAt()) {
                    this.currentToken = this.matcher.group();
                    this.matcher.region(this.matcher.end(), this.matcher.regionEnd());
                } else {
                    this.currentToken = String.valueOf(this.text.charAt(this.pos));
                    this.matcher.region(this.pos + 1, this.matcher.regionEnd());
                }
                this.skipWhitespace();
            }
        }

        private void skipWhitespace() {
            this.matcher.usePattern(WHITESPACE);
            if (this.matcher.lookingAt()) {
                this.matcher.region(this.matcher.end(), this.matcher.regionEnd());
            }
        }

        public boolean tryConsume(String token) {
            if (this.currentToken.equals(token)) {
                this.nextToken();
                return true;
            }
            return false;
        }

        public void consume(String token) throws ParseException {
            if (!this.tryConsume(token)) {
                String string = String.valueOf(String.valueOf(token));
                throw this.parseException(new StringBuilder(12 + string.length()).append("Expected \"").append(string).append("\".").toString());
            }
        }

        public boolean lookingAtInteger() {
            if (this.currentToken.length() == 0) {
                return false;
            }
            char c = this.currentToken.charAt(0);
            return '0' <= c && c <= '9' || c == '-' || c == '+';
        }

        public boolean lookingAt(String text) {
            return this.currentToken.equals(text);
        }

        public String consumeIdentifier() throws ParseException {
            for (int i = 0; i < this.currentToken.length(); ++i) {
                char c = this.currentToken.charAt(i);
                if ('a' <= c && c <= 'z' || 'A' <= c && c <= 'Z' || '0' <= c && c <= '9' || c == '_' || c == '.') continue;
                String string = String.valueOf(String.valueOf(this.currentToken));
                throw this.parseException(new StringBuilder(29 + string.length()).append("Expected identifier. Found '").append(string).append("'").toString());
            }
            String result = this.currentToken;
            this.nextToken();
            return result;
        }

        public boolean tryConsumeIdentifier() {
            try {
                this.consumeIdentifier();
                return true;
            } catch (ParseException e) {
                return false;
            }
        }

        public int consumeInt32() throws ParseException {
            try {
                int result = TextFormat.parseInt32(this.currentToken);
                this.nextToken();
                return result;
            } catch (NumberFormatException e) {
                throw this.integerParseException(e);
            }
        }

        public int consumeUInt32() throws ParseException {
            try {
                int result = TextFormat.parseUInt32(this.currentToken);
                this.nextToken();
                return result;
            } catch (NumberFormatException e) {
                throw this.integerParseException(e);
            }
        }

        public long consumeInt64() throws ParseException {
            try {
                long result = TextFormat.parseInt64(this.currentToken);
                this.nextToken();
                return result;
            } catch (NumberFormatException e) {
                throw this.integerParseException(e);
            }
        }

        public boolean tryConsumeInt64() {
            try {
                this.consumeInt64();
                return true;
            } catch (ParseException e) {
                return false;
            }
        }

        public long consumeUInt64() throws ParseException {
            try {
                long result = TextFormat.parseUInt64(this.currentToken);
                this.nextToken();
                return result;
            } catch (NumberFormatException e) {
                throw this.integerParseException(e);
            }
        }

        public boolean tryConsumeUInt64() {
            try {
                this.consumeUInt64();
                return true;
            } catch (ParseException e) {
                return false;
            }
        }

        public double consumeDouble() throws ParseException {
            if (DOUBLE_INFINITY.matcher(this.currentToken).matches()) {
                boolean negative = this.currentToken.startsWith("-");
                this.nextToken();
                return negative ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
            }
            if (this.currentToken.equalsIgnoreCase("nan")) {
                this.nextToken();
                return Double.NaN;
            }
            try {
                double result = Double.parseDouble(this.currentToken);
                this.nextToken();
                return result;
            } catch (NumberFormatException e) {
                throw this.floatParseException(e);
            }
        }

        public boolean tryConsumeDouble() {
            try {
                this.consumeDouble();
                return true;
            } catch (ParseException e) {
                return false;
            }
        }

        public float consumeFloat() throws ParseException {
            if (FLOAT_INFINITY.matcher(this.currentToken).matches()) {
                boolean negative = this.currentToken.startsWith("-");
                this.nextToken();
                return negative ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY;
            }
            if (FLOAT_NAN.matcher(this.currentToken).matches()) {
                this.nextToken();
                return Float.NaN;
            }
            try {
                float result = Float.parseFloat(this.currentToken);
                this.nextToken();
                return result;
            } catch (NumberFormatException e) {
                throw this.floatParseException(e);
            }
        }

        public boolean tryConsumeFloat() {
            try {
                this.consumeFloat();
                return true;
            } catch (ParseException e) {
                return false;
            }
        }

        public boolean consumeBoolean() throws ParseException {
            if (this.currentToken.equals("true") || this.currentToken.equals("t") || this.currentToken.equals("1")) {
                this.nextToken();
                return true;
            }
            if (this.currentToken.equals("false") || this.currentToken.equals("f") || this.currentToken.equals("0")) {
                this.nextToken();
                return false;
            }
            throw this.parseException("Expected \"true\" or \"false\".");
        }

        public String consumeString() throws ParseException {
            return this.consumeByteString().toStringUtf8();
        }

        public boolean tryConsumeString() {
            try {
                this.consumeString();
                return true;
            } catch (ParseException e) {
                return false;
            }
        }

        public ByteString consumeByteString() throws ParseException {
            ArrayList<ByteString> list = new ArrayList<ByteString>();
            this.consumeByteString(list);
            while (this.currentToken.startsWith("'") || this.currentToken.startsWith("\"")) {
                this.consumeByteString(list);
            }
            return ByteString.copyFrom(list);
        }

        private void consumeByteString(List<ByteString> list) throws ParseException {
            char quote;
            char c = quote = this.currentToken.length() > 0 ? this.currentToken.charAt(0) : (char)'\u0000';
            if (quote != '\"' && quote != '\'') {
                throw this.parseException("Expected string.");
            }
            if (this.currentToken.length() < 2 || this.currentToken.charAt(this.currentToken.length() - 1) != quote) {
                throw this.parseException("String missing ending quote.");
            }
            try {
                String escaped = this.currentToken.substring(1, this.currentToken.length() - 1);
                ByteString result = TextFormat.unescapeBytes(escaped);
                this.nextToken();
                list.add(result);
            } catch (InvalidEscapeSequenceException e) {
                throw this.parseException(e.getMessage());
            }
        }

        public ParseException parseException(String description) {
            return new ParseException(this.line + 1, this.column + 1, description);
        }

        public ParseException parseExceptionPreviousToken(String description) {
            return new ParseException(this.previousLine + 1, this.previousColumn + 1, description);
        }

        private ParseException integerParseException(NumberFormatException e) {
            String string = String.valueOf(e.getMessage());
            return this.parseException(string.length() != 0 ? "Couldn't parse integer: ".concat(string) : new String("Couldn't parse integer: "));
        }

        private ParseException floatParseException(NumberFormatException e) {
            String string = String.valueOf(e.getMessage());
            return this.parseException(string.length() != 0 ? "Couldn't parse number: ".concat(string) : new String("Couldn't parse number: "));
        }
    }

    private static final class TextGenerator {
        private final Appendable output;
        private final StringBuilder indent = new StringBuilder();
        private boolean atStartOfLine = true;

        private TextGenerator(Appendable output) {
            this.output = output;
        }

        public void indent() {
            this.indent.append("  ");
        }

        public void outdent() {
            int length = this.indent.length();
            if (length == 0) {
                throw new IllegalArgumentException(" Outdent() without matching Indent().");
            }
            this.indent.delete(length - 2, length);
        }

        public void print(CharSequence text) throws IOException {
            int size = text.length();
            int pos = 0;
            for (int i = 0; i < size; ++i) {
                if (text.charAt(i) != '\n') continue;
                this.write(text.subSequence(pos, i + 1));
                pos = i + 1;
                this.atStartOfLine = true;
            }
            this.write(text.subSequence(pos, size));
        }

        private void write(CharSequence data) throws IOException {
            if (data.length() == 0) {
                return;
            }
            if (this.atStartOfLine) {
                this.atStartOfLine = false;
                this.output.append(this.indent);
            }
            this.output.append(data);
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static final class Printer {
        boolean singleLineMode = false;
        boolean escapeNonAscii = true;

        private Printer() {
        }

        private Printer setSingleLineMode(boolean singleLineMode) {
            this.singleLineMode = singleLineMode;
            return this;
        }

        private Printer setEscapeNonAscii(boolean escapeNonAscii) {
            this.escapeNonAscii = escapeNonAscii;
            return this;
        }

        private void print(MessageOrBuilder message, TextGenerator generator) throws IOException {
            for (Map.Entry<Descriptors.FieldDescriptor, Object> field : message.getAllFields().entrySet()) {
                this.printField(field.getKey(), field.getValue(), generator);
            }
            this.printUnknownFields(message.getUnknownFields(), generator);
        }

        private void printField(Descriptors.FieldDescriptor field, Object value, TextGenerator generator) throws IOException {
            if (field.isRepeated()) {
                for (Object element : (List)value) {
                    this.printSingleField(field, element, generator);
                }
            } else {
                this.printSingleField(field, value, generator);
            }
        }

        private void printSingleField(Descriptors.FieldDescriptor field, Object value, TextGenerator generator) throws IOException {
            if (field.isExtension()) {
                generator.print("[");
                if (field.getContainingType().getOptions().getMessageSetWireFormat() && field.getType() == Descriptors.FieldDescriptor.Type.MESSAGE && field.isOptional() && field.getExtensionScope() == field.getMessageType()) {
                    generator.print(field.getMessageType().getFullName());
                } else {
                    generator.print(field.getFullName());
                }
                generator.print("]");
            } else if (field.getType() == Descriptors.FieldDescriptor.Type.GROUP) {
                generator.print(field.getMessageType().getName());
            } else {
                generator.print(field.getName());
            }
            if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                if (this.singleLineMode) {
                    generator.print(" { ");
                } else {
                    generator.print(" {\n");
                    generator.indent();
                }
            } else {
                generator.print(": ");
            }
            this.printFieldValue(field, value, generator);
            if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                if (this.singleLineMode) {
                    generator.print("} ");
                } else {
                    generator.outdent();
                    generator.print("}\n");
                }
            } else if (this.singleLineMode) {
                generator.print(" ");
            } else {
                generator.print("\n");
            }
        }

        private void printFieldValue(Descriptors.FieldDescriptor field, Object value, TextGenerator generator) throws IOException {
            switch (field.getType()) {
                case INT32: 
                case SINT32: 
                case SFIXED32: {
                    generator.print(((Integer)value).toString());
                    break;
                }
                case INT64: 
                case SINT64: 
                case SFIXED64: {
                    generator.print(((Long)value).toString());
                    break;
                }
                case BOOL: {
                    generator.print(((Boolean)value).toString());
                    break;
                }
                case FLOAT: {
                    generator.print(((Float)value).toString());
                    break;
                }
                case DOUBLE: {
                    generator.print(((Double)value).toString());
                    break;
                }
                case UINT32: 
                case FIXED32: {
                    generator.print(TextFormat.unsignedToString((Integer)value));
                    break;
                }
                case UINT64: 
                case FIXED64: {
                    generator.print(TextFormat.unsignedToString((Long)value));
                    break;
                }
                case STRING: {
                    generator.print("\"");
                    generator.print(this.escapeNonAscii ? TextFormat.escapeText((String)value) : TextFormat.escapeDoubleQuotesAndBackslashes((String)value));
                    generator.print("\"");
                    break;
                }
                case BYTES: {
                    generator.print("\"");
                    if (value instanceof ByteString) {
                        generator.print(TextFormat.escapeBytes((ByteString)value));
                    } else {
                        generator.print(TextFormat.escapeBytes((byte[])value));
                    }
                    generator.print("\"");
                    break;
                }
                case ENUM: {
                    generator.print(((Descriptors.EnumValueDescriptor)value).getName());
                    break;
                }
                case MESSAGE: 
                case GROUP: {
                    this.print((Message)value, generator);
                }
            }
        }

        private void printUnknownFields(UnknownFieldSet unknownFields, TextGenerator generator) throws IOException {
            for (Map.Entry<Integer, UnknownFieldSet.Field> entry : unknownFields.asMap().entrySet()) {
                int number = entry.getKey();
                UnknownFieldSet.Field field = entry.getValue();
                this.printUnknownField(number, 0, field.getVarintList(), generator);
                this.printUnknownField(number, 5, field.getFixed32List(), generator);
                this.printUnknownField(number, 1, field.getFixed64List(), generator);
                this.printUnknownField(number, 2, field.getLengthDelimitedList(), generator);
                for (UnknownFieldSet value : field.getGroupList()) {
                    generator.print(entry.getKey().toString());
                    if (this.singleLineMode) {
                        generator.print(" { ");
                    } else {
                        generator.print(" {\n");
                        generator.indent();
                    }
                    this.printUnknownFields(value, generator);
                    if (this.singleLineMode) {
                        generator.print("} ");
                        continue;
                    }
                    generator.outdent();
                    generator.print("}\n");
                }
            }
        }

        private void printUnknownField(int number, int wireType, List<?> values2, TextGenerator generator) throws IOException {
            for (Object value : values2) {
                generator.print(String.valueOf(number));
                generator.print(": ");
                TextFormat.printUnknownFieldValue(wireType, value, generator);
                generator.print(this.singleLineMode ? " " : "\n");
            }
        }

        static /* synthetic */ Printer access$100(Printer x0, boolean x1) {
            return x0.setSingleLineMode(x1);
        }

        static /* synthetic */ Printer access$200(Printer x0, boolean x1) {
            return x0.setEscapeNonAscii(x1);
        }
    }
}

