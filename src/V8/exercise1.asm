section .data
    A db 10      ; Define A as a byte with value 10
    C db 20      ; Define C as a byte with value 20

section .text
    global _start    ; Make the '_start' label visible

_start:
    ; Example of how you might use A and C
    ; Load value of A into the AL register
    mov al, [A]      ; AL = A
    ; Add the value of C to AL
    add al, [C]      ; AL = AL + C (AL = 10 + 20 = 30)

    ; Now AL contains the result, for example:
    ; You might want to do something with this result, like printing or returning from the program.

    ; Exit the program
    mov eax, 60      ; sys_exit system call
    xor edi, edi     ; Return code 0
    syscall          ; Make the system call to exit
