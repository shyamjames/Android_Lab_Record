#!/usr/bin/env python3
"""
build.py – Generate Android_Lab_Record.tex with all code rows inlined.
This avoids \\input inside longtable (which breaks longtable's row tracking).
Run this script then compile with tectonic.
"""

import os

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
    result = []
    for ch in text:
        if ch in ESCAPES:
            result.append(ESCAPES[ch])
        elif ch == ' ':
            result.append(r'\ ')
        elif ch == '\t':
            result.append(r'\ \ \ \ ')
        else:
            result.append(ch)
    return ''.join(result)


def code_rows(filepath):
    """Return longtable rows for every line of the file, as a string."""
    with open(filepath, 'r', encoding='utf-8') as f:
        lines = f.readlines()
    rows = []
    for line in lines:
        content = line.rstrip('\n\r')
        if content.strip() == '':
            rows.append(r'\strut \\')
        else:
            esc = escape(content)
            rows.append(r'{\ttfamily\small ' + esc + r'} \\')
    return '\n'.join(rows)


PREAMBLE = r"""\documentclass[a4paper,12pt]{article}
\usepackage[margin=1in]{geometry}
\usepackage{array}
\usepackage{longtable}
\usepackage{listings}
\usepackage{xcolor}
\usepackage{graphicx}
\usepackage{tikz}
\usetikzlibrary{calc}
\usepackage{eso-pic}

\AddToShipoutPictureBG{%
  \begin{tikzpicture}[remember picture,overlay]
    \draw[line width=1pt] ($(current page.north west) + (2.2cm,-2.2cm)$) rectangle ($(current page.south east) + (-2.2cm,2.2cm)$);
  \end{tikzpicture}%
}

\begin{document}
\pagestyle{plain}
"""

TABLE_HEAD = r"""\noindent\begingroup
\renewcommand{\arraystretch}{1.1}
\begin{longtable}{|p{0.94\textwidth}|}
\hline\endfirsthead\hline\endhead\hline\endfoot\hline\endlastfoot
"""

TABLE_FOOT = r"""\end{longtable}
\endgroup
"""


def program_block(number, date, title, java_file, xml_file, output_row):
    sep = '\n\\hline\n'
    # \rule{0pt}{13pt} is a strut that gives label/header rows extra height
    S = r'\rule{0pt}{13pt}'
    return (
        TABLE_HEAD
        + f'{S}Program No: {number} \\hfill Date: {date} \\\\\n'
        + '\\hline\n'
        + f'{S}Program Title: {title} \\\\\n'
        + '\\hline\n'
        + f'{S}\\textbf{{MainActivity.java:}} \\\\\n'
        + '\\hline\n'
        + code_rows(java_file)
        + sep
        + f'{S}\\textbf{{activity\\_main.xml:}} \\\\\n'
        + '\\hline\n'
        + code_rows(xml_file)
        + sep
        + f'Output: \\newline \\begin{{center}} {output_row} \\end{{center}} \\\\\n'
        + TABLE_FOOT
    )


PROGRAMS = [
    {
        'number': 1,
        'date': '29/06/2026',
        'title': 'Create an app that displays name and email id.',
        'java': 'code/program1/MainActivity.java',
        'xml':  'code/program1/activity_main.xml',
        'output': r'\includegraphics[width=0.45\textwidth]{Output_Screenshots/1.png}',
    },
    {
        'number': 2,
        'date': '29/06/2026',
        'title': 'Create an app that takes marks of any three subjects from the user and displays its sum and average.',
        'java': 'code/program2/MainActivity.java',
        'xml':  'code/program2/activity_main.xml',
        'output': r'\includegraphics[width=0.45\textwidth]{Output_Screenshots/2.png}',
    },
    {
        'number': 3,
        'date': '29/06/2026',
        'title': 'Create suitable apps to design the different layouts of the androidstudio.',
        'java': 'code/program3/MainActivity.java',
        'xml':  'code/program3/activity_main.xml',
        'output': r'\includegraphics[width=0.45\textwidth]{Output_Screenshots/3.png}',
    },
    {
        'number': 4,
        'date': '29/06/2026',
        'title': 'Develop a simple calculator app.',
        'java': 'code/program4/MainActivity.java',
        'xml':  'code/program4/activity_main.xml',
        'output': (r'\includegraphics[width=0.4\textwidth]{Output_Screenshots/4_1.png}'
                   r' \quad '
                   r'\includegraphics[width=0.4\textwidth]{Output_Screenshots/4_2.png}'),
    },
    {
        'number': 5,
        'date': '29/06/2026',
        'title': 'Create an app that changes text color when a button is clicked.',
        'java': 'code/program5/MainActivity.java',
        'xml':  'code/program5/activity_main.xml',
        'output': (r'\includegraphics[width=0.4\textwidth]{Output_Screenshots/5_1.png}'
                   r' \quad '
                   r'\includegraphics[width=0.4\textwidth]{Output_Screenshots/5_2.png}'),
    },
    {
        'number': 6,
        'date': '29/06/2026',
        'title': 'Create an app that displays Date and Time in different formats using Linear Layout.',
        'java': 'code/program6/MainActivity.java',
        'xml':  'code/program6/activity_main.xml',
        'output': (r'\includegraphics[width=0.4\textwidth]{Output_Screenshots/6_1.png}'
                   r' \quad '
                   r'\includegraphics[width=0.4\textwidth]{Output_Screenshots/6_2.png}'),
    },
    {
        'number': 7,
        'date': '29/06/2026',
        'title': 'Create an app that displays a Toast on the launch of an activity.',
        'java': 'code/program7/MainActivity.java',
        'xml':  'code/program7/activity_main.xml',
        'output': r'\includegraphics[width=0.45\textwidth]{Output_Screenshots/7.png}',
    },
    {
        'number': 8,
        'date': '29/06/2026',
        'title': 'Create an app that creates an Alert Dialog Box.',
        'java': 'code/program8/MainActivity.java',
        'xml':  'code/program8/activity_main.xml',
        'output': (r'\includegraphics[width=0.3\textwidth]{Output_Screenshots/8_1.png}'
                   r' \quad '
                   r'\includegraphics[width=0.3\textwidth]{Output_Screenshots/8_2.png}'
                   r' \quad '
                   r'\includegraphics[width=0.3\textwidth]{Output_Screenshots/8_3.png}'),
    },
    {
        'number': 9,
        'date': '29/06/2026',
        'title': 'Create a Greeting card app that displays an image.',
        'java': 'code/program9/MainActivity.java',
        'xml':  'code/program9/activity_main.xml',
        'output': r'\includegraphics[width=0.45\textwidth]{Output_Screenshots/9.png}',
    },
    {
        'number': 10,
        'date': '29/06/2026',
        'title': 'Create an app that plays audio when we click on the button ``Play Music" and plays video when we click on the button ``Play Video".',
        'java': 'code/program10/MainActivity.java',
        'xml':  'code/program10/activity_main.xml',
        'output': (r'\includegraphics[width=0.4\textwidth]{Output_Screenshots/10_1.png}'
                   r' \quad '
                   r'\includegraphics[width=0.4\textwidth]{Output_Screenshots/10_2.png}'),
    },
]


def build():
    parts = [PREAMBLE]
    for i, prog in enumerate(PROGRAMS):
        if i > 0:
            parts.append('\n\\newpage\n\n')
        parts.append(f'%% ─── Program {prog["number"]} ──────────────────────────────────────────────\n')
        parts.append(program_block(
            prog['number'], prog['date'], prog['title'],
            prog['java'], prog['xml'], prog['output']
        ))
    parts.append('\n\\end{document}\n')

    tex = ''.join(parts)
    with open('Android_Lab_Record.tex', 'w', encoding='utf-8') as f:
        f.write(tex)
    print(f"Written Android_Lab_Record.tex ({len(tex):,} bytes)")


if __name__ == '__main__':
    build()
