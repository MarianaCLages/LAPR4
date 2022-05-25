grammar GrammarSurvey;

prog: survey+;

//Survey
survey: ID NEWLINE MESSAGE NEWLINE (section  MESSAGE)+;



//Section
section: 'Section' ID NEWLINE MESSAGE NEWLINE MESSAGE NEWLINE OBLIGATORINESS NEWLINE REPEATABLE NEWLINE question+;


//Questions
question: 'Question' ID NEWLINE MESSAGE NEWLINE TYPE NEWLINE OBLIGATORINESS NEWLINE MESSAGE NEWLINE;

//INSTRUCTION: SETENCE INSTRUCTION | SETENCE;

TYPE: 'Free-Text '| 'Numeric ' | 'Single-Choice ' | 'Single-Choice with input value ' | 'Multiple-Choice '|'Multiple-Choice with input value '
| 'Sorting Options ' | 'Scaling Options ';
OBLIGATORINESS: 'mandatory '|'optional '|'condition dependent ';
REPEATABLE: | 'repeatable ' | 'non-repeatable ';
//EXTRAINFO: SETENCE EXTRAINFO | SETENCE;


MESSAGE: SETENCE MESSAGE | SETENCE | |;
//Formats
//MESSAGE: SETENCE MESSAGE | SETENCE LINE MESSAGE | SETENCE LINE | SETENCE;

SETENCE: WORD SETENCE | WORD SYMBOL SETENCE | WORD PONTUATION;
ID: (WORD|INT)+;


NEWLINE: [\r\n]+;
SYMBOL: '#'|'$'|'%'|'&'|'/'|'('|')'|'='|'¿'|'*'|'+'|'-'|'_'|':'|';'|'{'|'}'|'['|']'|'|'|'^'|'~'|'<'|'>'|'@'|'"';
PONTUATION: '!'|'?'|.;
WORD: [A-z]+;
INT: [0-9]+;
DNOT: EOF;