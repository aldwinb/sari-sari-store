" automatic reloading of .vimrc
autocmd! bufwritepost .vimrc source %

" copy / paste
set pastetoggle=<F2>
set clipboard=unnamed

" mapleader
let mapleader=","

" mouse
set mouse=a
set bs=2

" quicksave
noremap <C-S> :update<CR>
vnoremap <C-S> <C-C> :update<CR>
inoremap <C-S> <C-0> :update<CR>

" switch windows
map <c-h> <c-w>h
map <c-j> <c-w>j
map <c-k> <c-w>k
map <c-l> <c-w>l

" switch tabs
map <Leader>n <esc>:tabprevious<CR>
map <Leader>m <esc>:tabnext<CR>

" indentation
vnoremap < <gv 
vnoremap > >gv

" spaces
" autocmd ColorScheme * highlight ExtraWhitespace ctermbg=red guibg=red
" au InsertLeave * match ExtraWhitespace /\s\+$/

" syntax highlighting
filetype off
filetype plugin indent on
syntax on

set number
set tabstop=2
set shiftwidth=2
set expandtab
set textwidth=79  " lines longer than 79 columns will be broken
set softtabstop=2 " insert/delete 4 spaces when hitting a TAB/BACKSPACE
set shiftround    " round indent to multiple of 'shiftwidth'
set autoindent
set colorcolumn=80
highlight ColorColumn ctermbg=233

set t_Co=256
colorscheme distinguished
" setup pathogen to manage plugins
" mkdir -p ~/.vim/bundle ~/.vim/autoload
" curl -LSso ~/.vim/autoload/pathogen.vim https://tpo.pe/pathogen.vim
" call pathogen#infect()

" =============== PLUGINS ==================
" python-mode
" cd ~/.vim/bundle
" git clone https://github.com/klen/python-mode
" map <Leader>g :call RopeGotoDefinition()<CR>
" let ropevim_enable_shortcuts=1
" let g:pymode_rope_goto_def_newwin = "vnew"
" let g:pymode_rope_extended_complete = 1
" let g:pymode_breakpoint = 0
" let g:pymode_syntax = 1
" let g:pymode_syntax_builtin_objs = 0
" let g:pymode_syntax_builtin_funcs = 0
" map <Leader>b Oimport ipdb; ipdb.set_trace() # BREAKPOINT<C-c>
