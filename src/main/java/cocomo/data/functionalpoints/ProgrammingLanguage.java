package cocomo.data.functionalpoints;

public enum ProgrammingLanguage {
    DOS("Пакетні файли DOS", 128),
    BASIC("Basic", 107),
    PL_1("PL/1", 80),
    C_SHARP("C#", 58),
    LISP("Розширений LISP", 56),
    JAVA("Java", 55),
    JAVASCRIPT("JavaScript", 54),
    C_PLUS_PLUS("C++", 53),
    VISUAL_BASIC("Visual Basic", 50),
    DATABASE_LANGUAGES("Мови баз даних", 40),
    ACCESS("Access", 38),
    VBSCRIPT("VBScript", 38),
    DSS("Мови підтримки прийняття", 35),
    FOXPRO("FoxPro 2.5", 34),
    DELPHI("DELPHI", 29),
    STANDARD_OBJECT_ORIENTED("Стандартні об’єктно-орієнтовані", 29),
    VBNET("VB.Net ", 28),
    STANDARDLANG_4THGENERATION("Стандартні мови 4-го покоління", 20),
    HTML("HTML 3.0", 15),
    SQL("SQL", 13),
    SQL_FORMS("SQL Forms", 11),
    EXCEL("EXCEL", 6);

    public final String name;
    public final double codeLines;

    ProgrammingLanguage(String name, int codeLines) {
        this.name = name;
        this.codeLines = codeLines;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
