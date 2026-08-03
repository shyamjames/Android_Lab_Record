#!/usr/bin/env python3
"""
gen_code.py – Generate per-line longtable rows for each source file.
Each line of code becomes one \multicolumn row so longtable can break
between any two lines, eliminating page-end gaps.
"""

import os

# Characters that need escaping in LaTeX text mode
ESCAPES = {
    '\\': r'\textbackslash{}',
    '{':  r'\{',
    '}':  r'\}',
    '#':  r'\#',
    '$':  r'\$',
    '%':  r'\%',
    '&':  r'\&',
    '^':  r'\^{}',
    '_':  r'\_',
    '~':  r'\textasciitilde{}',
    '<':  r'\textless{}',
    '>':  r'\textgreater{}',
}

def escape(text):
    """Escape LaTeX special characters, preserving spaces as explicit TeX spaces."""
    result = []
    for ch in text:
        if ch in ESCAPES:
            result.append(ESCAPES[ch])
        elif ch == ' ':
            result.append(r'\ ')   # explicit non-collapsing space
        elif ch == '\t':
            result.append(r'\ \ \ \ ')  # 4 spaces for a tab
        else:
            result.append(ch)
    return ''.join(result)


def generate_tex(infile, outfile):
    os.makedirs(os.path.dirname(outfile), exist_ok=True)
    with open(infile, 'r', encoding='utf-8') as f:
        lines = f.readlines()

    rows = []
    for line in lines:
        content = line.rstrip('\n\r')
        if content.strip() == '':
            # Empty line – use \strut to preserve row height
            rows.append(r'\strut \\')
        else:
            esc = escape(content)
            rows.append(r'{\ttfamily\small ' + esc + r'} \\')

    with open(outfile, 'w', encoding='utf-8') as f:
        f.write('\n'.join(rows) + '\n')
    print(f"  Generated {outfile}  ({len(lines)} lines → {len(rows)} rows)")



PROGRAMS = [
    ('code/program1/MainActivity.java',   'generated/p1_java.tex'),
    ('code/program1/activity_main.xml',   'generated/p1_xml.tex'),
    ('code/program2/MainActivity.java',   'generated/p2_java.tex'),
    ('code/program2/activity_main.xml',   'generated/p2_xml.tex'),
    ('code/program3/MainActivity.java',   'generated/p3_java.tex'),
    ('code/program3/activity_main.xml',   'generated/p3_xml.tex'),
    ('code/program4/MainActivity.java',   'generated/p4_java.tex'),
    ('code/program4/activity_main.xml',   'generated/p4_xml.tex'),
    ('code/program5/MainActivity.java',   'generated/p5_java.tex'),
    ('code/program5/activity_main.xml',   'generated/p5_xml.tex'),
    ('code/program6/MainActivity.java',   'generated/p6_java.tex'),
    ('code/program6/activity_main.xml',   'generated/p6_xml.tex'),
]

if __name__ == '__main__':
    print("Generating per-line TeX fragments...")
    for infile, outfile in PROGRAMS:
        generate_tex(infile, outfile)
    print("Done.")
